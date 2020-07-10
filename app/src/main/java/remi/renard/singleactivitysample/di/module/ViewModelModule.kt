package remi.renard.singleactivitysample.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import remi.renard.singleactivitysample.di.ViewModelFactory
import remi.renard.singleactivitysample.di.ViewModelKey
import remi.renard.singleactivitysample.ui.home.HomeViewModel
import remi.renard.singleactivitysample.ui.home.users.UsersViewModel
import remi.renard.singleactivitysample.ui.splash.SplashScreenViewModel

/**
 * Module that provides all dependencies from the presentation package/layer.
 */
@Module
@Suppress("unused")
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(SplashScreenViewModel::class)
    abstract fun bindSplashScreenViewModel(viewModel: SplashScreenViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(viewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(UsersViewModel::class)
    abstract fun bindUsersViewModel(viewModel: UsersViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
