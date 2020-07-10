package remi.renard.singleactivitysample.domain.interactor

import androidx.lifecycle.Observer

/**
 * An abstract [Observer] that make the boilerplate code to match with the different states of [Resource];
 *
 * @property hideLoading The action to be performed to hide the loading state.
 * @property showLoading The action to be performed to show the loading state.
 * @property onSuccess The action to be performed to show the success state.
 * @property onError The action to be performed to show the error state.
 *
 * @param T The type of elements held.
 */
class ResourceObserver<T>(
    private val hideLoading: () -> Unit,
    private val showLoading: () -> Unit,
    private val onSuccess: (data: T?) -> Unit,
    private val onError: (data: T?, message: String) -> Unit
) : Observer<Resource<T>> {

    override fun onChanged(resource: Resource<T>?) {
        when (resource?.status) {
            Status.LOADING -> {
                showLoading()
            }
            Status.SUCCESS -> {
                hideLoading()
                onSuccess(resource.data)
            }
            Status.ERROR -> {
                hideLoading()
                onError(resource.data, resource.message ?: "")
            }
        }
    }
}
