package remi.renard.singleactivitysample.extensions

import android.os.Build
import android.text.Html
import android.text.Spanned

fun Boolean?.toInt(): Int {
    return if (this == true) 1 else 0
}

fun Int?.toBoolean(): Boolean {
    return this != null && this >= 1
}

fun String?.toHtml(): Spanned {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(this, Html.FROM_HTML_MODE_COMPACT)
    } else {
        @Suppress("DEPRECATION")
        Html.fromHtml(this)
    }
}
