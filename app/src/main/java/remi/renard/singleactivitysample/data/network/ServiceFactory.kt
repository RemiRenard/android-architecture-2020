package remi.renard.singleactivitysample.data.network

import com.squareup.moshi.Moshi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import remi.renard.singleactivitysample.data.network.adapter.LiveDataCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class ServiceFactory @Inject constructor(
    val moshi: Moshi,
    private val interceptors: Set<Interceptor>
) {

    private fun makeOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .addInterceptor {
                val original = it.request()
                val request = original.newBuilder()
                // Request customization: add request headers
                // request.header("Authorization", me.accessToken)
                request.method(original.method(), original.body())
                it.proceed(request.build())
            }
            .connectTimeout(TIMEOUT_CONNECT, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT_READ, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT_WRITE, TimeUnit.SECONDS)

        interceptors.forEach {
            builder.addInterceptor(it)
        }

        return builder.build()
    }

    fun makeRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(makeOkHttpClient())
            .addCallAdapterFactory(LiveDataCallAdapterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    companion object {
        const val TIMEOUT_CONNECT = 10.toLong()
        const val TIMEOUT_READ = 10.toLong()
        const val TIMEOUT_WRITE = 10.toLong()

        const val BASE_URL = "https://api.github.com/"
    }
}
