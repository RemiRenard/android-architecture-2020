package remi.renard.singleactivitysample.data.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class HeadersInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader(CONTENT_TYPE_HEADER, APPLICATION_JSON)
            .build()

        return chain.proceed(request)
    }

    companion object {
        private const val CONTENT_TYPE_HEADER = "Content-Type"
        private const val APPLICATION_JSON = "application/json"
    }
}
