package remi.renard.singleactivitysample.data.network.interceptor

import android.util.Log
import okhttp3.logging.HttpLoggingInterceptor
import remi.renard.singleactivitysample.BuildConfig

private val logger = HttpLoggingInterceptor.Logger {
    Log.v("OkHttp", it)
}

val httpLoggingInterceptor: HttpLoggingInterceptor by lazy {
    val logging = HttpLoggingInterceptor(logger)

    if (BuildConfig.DEBUG) {
        logging.level = HttpLoggingInterceptor.Level.BODY
    } else {
        logging.level = HttpLoggingInterceptor.Level.NONE
    }

    logging
}
