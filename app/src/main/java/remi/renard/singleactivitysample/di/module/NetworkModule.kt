package remi.renard.singleactivitysample.di.module

import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import remi.renard.singleactivitysample.data.network.ServiceFactory
import remi.renard.singleactivitysample.data.network.interceptor.HeadersInterceptor
import remi.renard.singleactivitysample.data.network.interceptor.httpLoggingInterceptor
import remi.renard.singleactivitysample.data.network.service.UserService
import remi.renard.singleactivitysample.data.network.source.UserNetworkDataSource
import javax.inject.Singleton

/**
 * Module that provides all dependencies from the remote package/layer.
 */
@Module
object NetworkModule {

    // region Service

    @Provides
    @Singleton
    fun provideUserService(serviceFactory: ServiceFactory): UserService {
        return serviceFactory.makeRetrofit().create(UserService::class.java)
    }

    // endregion

    // region Data Source

    @Provides
    @Singleton
    fun provideUserNetworkDataSource(service: UserService): UserNetworkDataSource {
        return UserNetworkDataSource(service)
    }

    // endregion

    @Provides
    fun provideInterceptor(): Set<Interceptor> {
        return setOf(
            HeadersInterceptor(),
            httpLoggingInterceptor
        )
    }

    @Provides
    @Singleton
    fun provideServiceFactory(
        moshi: Moshi,
        interceptors: Set<@JvmSuppressWildcards Interceptor>
    ): ServiceFactory {
        return ServiceFactory(moshi, interceptors)
    }
}
