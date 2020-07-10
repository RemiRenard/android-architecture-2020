package remi.renard.singleactivitysample.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import remi.renard.singleactivitysample.MyApp
import remi.renard.singleactivitysample.di.module.*
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ApplicationModule::class,
        DbModule::class,
        NetworkModule::class,
        SharedPreferencesModule::class,
        UiActivityModule::class,
        UiFragmentModule::class,
        ViewModelModule::class
    ]
)
interface ApplicationComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }

    fun inject(app: MyApp)
}
