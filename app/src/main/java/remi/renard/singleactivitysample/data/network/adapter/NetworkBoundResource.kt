package remi.renard.singleactivitysample.data.network.adapter

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import remi.renard.singleactivitysample.domain.executor.AppExecutors
import remi.renard.singleactivitysample.domain.interactor.AbsentLiveData
import remi.renard.singleactivitysample.domain.interactor.Resource

/**
 * A generic class that can provide a resource backed by both the sqlite database and the network.
 *
 * @param <ResultType>
 * @param <RequestType>
</RequestType></ResultType> */
abstract class NetworkBoundResource<ResultType, RequestType>
@MainThread constructor(private val appExecutors: AppExecutors, withDatabase: Boolean = true) {

    private val result = MediatorLiveData<Resource<ResultType>>()

    init {
        result.value = Resource.loading(null)

        if (withDatabase) {
            fetchFromNetworkAndDatabase()
        } else {
            fetchFromNetwork()
        }
    }

    @MainThread
    private fun setValue(newValue: Resource<ResultType>) {
        if (result.value != newValue) {
            result.value = newValue
        }
    }

    private fun fetchFromNetwork() {
        val apiResponse = createCall()

        result.addSource(apiResponse) { response ->
            result.removeSource(apiResponse)

            when (response) {
                is ApiSuccessResponse -> setValue(
                    Resource.success(
                        convertToResult(
                            processResponse(
                                response
                            )
                        )
                    )
                )
                is ApiEmptyResponse -> setValue(Resource.success(null))
                is ApiErrorResponse -> setValue(Resource.error(response.errorMessage, null))
            }
        }
    }

    private fun fetchFromNetworkAndDatabase() {
        val dbSource = loadFromDb()
        result.addSource(dbSource) { data ->
            result.removeSource(dbSource)
            if (shouldFetch(data)) {
                val apiResponse = createCall()
                // we re-attach dbSource as a new source, it will dispatch its latest value quickly
                result.addSource(dbSource) { newData ->
                    setValue(Resource.loading(newData))
                }
                result.addSource(apiResponse) { response ->
                    result.removeSource(apiResponse)
                    result.removeSource(dbSource)
                    when (response) {
                        is ApiSuccessResponse -> appExecutors.diskIO().execute {
                            saveCallResult(processResponse(response))
                            appExecutors.mainThread().execute {
                                // we specially request a new live data,
                                // otherwise we will get immediately last cached value,
                                // which may not be updated with latest results received from network.
                                result.addSource(loadFromDb()) { newData ->
                                    setValue(Resource.success(newData))
                                }
                            }
                        }
                        is ApiEmptyResponse -> appExecutors.mainThread().execute {
                            // reload from disk whatever we had
                            result.addSource(loadFromDb()) { newData ->
                                setValue(Resource.success(newData))
                            }
                        }
                        is ApiErrorResponse -> {
                            onFetchFailed()
                            result.addSource(dbSource) { newData ->
                                setValue(Resource.error(response.errorMessage, newData))
                            }
                        }
                    }
                }
            } else {
                result.addSource(dbSource) { newData ->
                    setValue(Resource.success(newData))
                }
            }
        }
    }

    protected open fun onFetchFailed() {}

    fun asLiveData() = result as LiveData<Resource<ResultType>>

    @WorkerThread
    protected open fun processResponse(response: ApiSuccessResponse<RequestType>) = response.body

    @WorkerThread
    protected open fun convertToResult(item: RequestType?): ResultType? = null

    @WorkerThread
    protected open fun saveCallResult(item: RequestType) {
    }

    @MainThread
    protected open fun shouldFetch(data: ResultType?): Boolean = true

    @MainThread
    protected open fun loadFromDb(): LiveData<ResultType> = AbsentLiveData.create()

    @MainThread
    protected abstract fun createCall(): LiveData<ApiResponse<RequestType>>
}
