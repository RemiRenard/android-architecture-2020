package remi.renard.singleactivitysample.extensions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.observe
import remi.renard.singleactivitysample.domain.interactor.Resource
import remi.renard.singleactivitysample.domain.interactor.ResourceObserver

/**
 * Hack to handle a nullable return of the observed [LiveData]
 */
inline fun <T> LifecycleOwner.observe(
    liveData: LiveData<T>,
    crossinline onChanged: (T?) -> Unit = {}
) {
    liveData.observe(this) { onChanged(it) }
}

fun <T> LifecycleOwner.observeResource(
    liveData: LiveData<Resource<T>>,
    hideLoading: () -> Unit = {},
    showLoading: () -> Unit = {},
    onSuccess: (T?) -> Unit = {},
    onError: (data: T?, message: String) -> Unit = { _, _ -> }
) = liveData.observe(this, ResourceObserver(hideLoading, showLoading, onSuccess, onError))
