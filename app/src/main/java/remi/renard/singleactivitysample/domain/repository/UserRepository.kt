package remi.renard.singleactivitysample.domain.repository

import androidx.lifecycle.LiveData
import remi.renard.singleactivitysample.data.db.dao.UserDao
import remi.renard.singleactivitysample.data.network.adapter.ApiResponse
import remi.renard.singleactivitysample.data.network.adapter.NetworkBoundResource
import remi.renard.singleactivitysample.data.network.source.UserNetworkDataSource
import remi.renard.singleactivitysample.domain.executor.AppExecutors
import remi.renard.singleactivitysample.domain.interactor.RateLimiter
import remi.renard.singleactivitysample.domain.interactor.Resource
import remi.renard.singleactivitysample.domain.model.User
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val appExecutors: AppExecutors,
    private val dbDataSource: UserDao,
    private val networkDataSource: UserNetworkDataSource
) {

    internal val usersRateLimiter = RateLimiter(GET_USER_LIST_CACHE_TIMEOUT, TimeUnit.SECONDS)

    fun getUsers(since: Int? = null, perPage: Int? = null): LiveData<Resource<List<User>>> {
        return object : NetworkBoundResource<List<User>, List<User>>(appExecutors) {
            override fun saveCallResult(item: List<User>) {
                val users = item ?: return
                dbDataSource.clearAndInsertUsers(users)
            }

            override fun shouldFetch(data: List<User>?): Boolean {
                return data == null || data.isEmpty() || usersRateLimiter.shouldFetch(
                    GET_USER_LIST_KEY
                )
            }

            override fun loadFromDb(): LiveData<List<User>> = dbDataSource.getUsers()

            override fun createCall(): LiveData<ApiResponse<List<User>>> =
                networkDataSource.getUsers(since, perPage)

            override fun onFetchFailed() {
                usersRateLimiter.reset(GET_USER_LIST_KEY)
            }
        }.asLiveData()
    }

    private companion object {
        const val GET_USER_LIST_KEY = "users"
        const val GET_USER_LIST_CACHE_TIMEOUT = 10
    }
}
