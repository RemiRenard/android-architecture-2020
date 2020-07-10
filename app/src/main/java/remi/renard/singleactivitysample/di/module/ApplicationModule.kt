package remi.renard.singleactivitysample.di.module

import android.app.Application
import android.content.Context
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import dagger.Module
import dagger.Provides
import java.util.*
import javax.inject.Singleton

/**
 * Module used to provide dependencies at an application-level.
 */
@Module
object ApplicationModule {

    @Provides
    fun bindContext(application: Application): Context {
        return application
    }

    @Provides
    @Singleton
    fun bindMoshi(): Moshi {
        return Moshi.Builder()
            .add(Date::class.java, Rfc3339DateJsonAdapter().nullSafe())
            .build()
    }
}
