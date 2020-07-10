package remi.renard.singleactivitysample.extensions

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

/**
 * Show the soft input.
 */
fun AppCompatActivity.showSoftInput() {
    val inputMethodManager =
        getSystemService(Activity.INPUT_METHOD_SERVICE) as? InputMethodManager ?: return
    var view = currentFocus
    if (view == null) {
        view = View(this)
        view.isFocusable = true
        view.isFocusableInTouchMode = true
        view.requestFocus()
    }
    inputMethodManager.showSoftInput(view, InputMethodManager.SHOW_FORCED)
    inputMethodManager.toggleSoftInput(
        InputMethodManager.SHOW_FORCED,
        InputMethodManager.HIDE_IMPLICIT_ONLY
    )
}

/**
 * Hide  the soft input.
 */
fun AppCompatActivity.hideSoftInput() {
    val inputMethodManager =
        getSystemService(Activity.INPUT_METHOD_SERVICE) as? InputMethodManager ?: return
    val view = currentFocus ?: View(this)
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

/**
 * Show the soft input.
 */
fun Fragment.showSoftInput() {
    val inputMethodManager =
        activity?.getSystemService(Activity.INPUT_METHOD_SERVICE) as? InputMethodManager ?: return
    var view = activity?.currentFocus
    if (view == null) {
        view = View(context)
        view.isFocusable = true
        view.isFocusableInTouchMode = true
        view.requestFocus()
    }
    inputMethodManager.showSoftInput(view, InputMethodManager.SHOW_FORCED)
}

/**
 * Hide  the soft input.
 */
fun Fragment.hideSoftInput() {
    val inputMethodManager =
        activity?.getSystemService(Activity.INPUT_METHOD_SERVICE) as? InputMethodManager ?: return
    val view = activity?.currentFocus ?: View(context)
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

fun View.showSoftInput() {
    val imm =
        context.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager ?: return
    isFocusable = true
    isFocusableInTouchMode = true
    requestFocus()
    imm.showSoftInput(this, InputMethodManager.SHOW_FORCED)
    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY)
}

fun View.hideSoftInput() {
    val inputMethodManager =
        context?.getSystemService(Activity.INPUT_METHOD_SERVICE) as? InputMethodManager ?: return
    inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
}
