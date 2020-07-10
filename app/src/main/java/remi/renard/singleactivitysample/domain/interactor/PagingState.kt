package remi.renard.singleactivitysample.domain.interactor

import androidx.lifecycle.LiveData
import androidx.paging.PagedList

/**
 * Data class that is necessary for a UI to show a listing and interact w/ the rest of the system.
 *
 * @param pagedList the LiveData of paged lists for the UI to observe
 * @param pagingState represents the network request status to show to the user
 * @param refreshState represents the refresh status to show to the user. Separate from networkState, this value is
 * importantly only when refresh is requested.
 * @param refresh refreshes the whole data and fetches it from scratch.
 * @param retry retries any failed requests.
 */
data class Listing<T>(
    val pagedList: LiveData<PagedList<T>>,
    val pagingState: LiveData<PagingState>,
    val refreshState: LiveData<PagingState>,
    val refresh: () -> Unit,
    val retry: () -> Unit
)

@Suppress("DataClassPrivateConstructor")
data class PagingState private constructor(val status: Status, val msg: String? = null) {

    companion object {
        val LOADED = PagingState(Status.SUCCESS)
        val LOADING = PagingState(Status.LOADING)
        fun error(msg: String?) = PagingState(Status.ERROR, msg)
    }
}
