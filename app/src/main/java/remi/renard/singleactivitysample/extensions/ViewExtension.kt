package remi.renard.singleactivitysample.extensions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes

/**
 * Inflate a [View] with given layoutId and attach it to the calling [ViewGroup].
 *
 * @param layoutRes Id of the layout to inflate.
 * @param attachToRoot Whether the inflated hierarchy should be attached to the root parameter.
 */
fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}