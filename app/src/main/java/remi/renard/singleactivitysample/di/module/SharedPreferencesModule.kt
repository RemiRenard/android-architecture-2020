package remi.renard.singleactivitysample.di.module

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.Reusable
import remi.renard.singleactivitysample.ui.SharedPreferencesManager

@Module
class SharedPreferencesModule {

    @Provides
    @Reusable
    fun provideSharedPreferences(context: Context): SharedPreferences {
        return context.applicationContext.getSharedPreferences(
            "${context.packageName}.$SHARED_PREFS_NAME",
            Context.MODE_PRIVATE
        )
    }

    @Provides
    @Reusable
    fun provideSharedPreferencesManager(sharedPreferences: SharedPreferences): SharedPreferencesManager {
        return SharedPreferencesManager(sharedPreferences)
    }

    private companion object {
        const val SHARED_PREFS_NAME = "appSharedPrefs"
    }
}
