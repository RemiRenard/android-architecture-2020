package remi.renard.singleactivitysample.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import remi.renard.singleactivitysample.ui.BaseActivity
import remi.renard.singleactivitysample.ui.home.HomeActivity
import remi.renard.singleactivitysample.ui.home.users.UsersFragment
import remi.renard.singleactivitysample.ui.splash.SplashScreenActivity

/**
 * Module that provides all dependencies from the ui package/layer and injects all activities.
 */
@Module
@Suppress("unused")
abstract class UiActivityModule {

    @ContributesAndroidInjector
    abstract fun contributeBaseActivity(): BaseActivity

    @ContributesAndroidInjector
    abstract fun contributeSplashScreenActivity(): SplashScreenActivity

    @ContributesAndroidInjector
    abstract fun contributeHomeActivity(): HomeActivity
}

/**
 * Module that provides all dependencies from the ui package/layer and injects all fragments.
 */
@Module
@Suppress("unused")
abstract class UiFragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeUsersFragment(): UsersFragment
}
