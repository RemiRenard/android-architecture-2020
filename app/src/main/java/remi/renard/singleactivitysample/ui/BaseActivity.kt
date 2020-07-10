package remi.renard.singleactivitysample.ui

import android.content.Intent
import android.os.Build
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import remi.renard.singleactivitysample.R

abstract class BaseActivity : AppCompatActivity {
    constructor() : super()
    constructor(@LayoutRes contentLayoutId: Int) : super(contentLayoutId)

    override fun startActivity(intent: Intent?) {
        super.startActivity(intent)
        overridePendingTransition(R.anim.full_slide_left_in, R.anim.half_slide_left_out)
    }

    override fun onBackPressed() {
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.Q && isTaskRoot) {
            // In Android 10, if you press back to finish an activity which is a task root,
            // that activity will leak
            finishAfterTransition()
        } else {
            super.onBackPressed()
        }
    }
}
