package remi.renard.singleactivitysample.data.network.adapter

import androidx.lifecycle.LiveData
import retrofit2.*
import retrofit2.CallAdapter.Factory
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import java.util.concurrent.atomic.AtomicBoolean

/**
 * A Retrofit adapter that converts the Call into a LiveData of ApiResponse.
 */
class LiveDataCallAdapterFactory : Factory() {

    override fun get(
        returnType: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {
        if (getRawType(returnType) != LiveData::class.java) {
            return null
        }

        val observableType = getParameterUpperBound(0, returnType as ParameterizedType)
        val rawObservableType = getRawType(observableType)

        if (rawObservableType != ApiResponse::class.java) {
            throw IllegalArgumentException("Type must be a resource.")
        }
        if (observableType !is ParameterizedType) {
            throw IllegalArgumentException("Resource must be parameterized.")
        }

        val bodyType = getParameterUpperBound(0, observableType)
        return ResponseCallAdapter<Any>(bodyType)
    }

    private class ResponseCallAdapter<R>(private val responseType: Type) :
        CallAdapter<R, LiveData<ApiResponse<R>>> {

        override fun responseType() = responseType

        override fun adapt(call: Call<R>): LiveData<ApiResponse<R>> {
            return object : LiveData<ApiResponse<R>>() {
                private var started = AtomicBoolean(false)

                override fun onActive() {
                    super.onActive()
                    if (started.compareAndSet(false, true)) {
                        call.enqueue(object : Callback<R> {
                            override fun onResponse(call: Call<R>, response: Response<R>) {
                                postValue(ApiResponse.create(response))
                            }

                            override fun onFailure(call: Call<R>, throwable: Throwable) {
                                postValue(ApiResponse.create(throwable))
                            }
                        })
                    }
                }
            }
        }
    }

    companion object {
        @JvmStatic
        fun create() = LiveDataCallAdapterFactory()
    }
}
