package remi.renard.singleactivitysample.ui.splash

import android.content.Context
import android.content.Intent
import android.os.Handler
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import remi.renard.singleactivitysample.R
import remi.renard.singleactivitysample.di.Injectable
import remi.renard.singleactivitysample.ui.BaseActivity
import remi.renard.singleactivitysample.ui.SharedPreferencesManager
import remi.renard.singleactivitysample.ui.home.HomeActivity
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SplashScreenActivity : BaseActivity(R.layout.activity_splash), Injectable {

    @Inject
    lateinit var sharedPreferencesManager: SharedPreferencesManager

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<SplashScreenViewModel> { viewModelFactory }

    private val handler by lazy { Handler() }

    private val runnable = Runnable {
        startActivity(HomeActivity.callingIntent(this))
    }

    override fun onResume() {
        super.onResume()
        handler.postDelayed(runnable, getSplashScreenDuration())
    }

    override fun onPause() {
        handler.removeCallbacks(runnable)
        super.onPause()
    }

    private fun getSplashScreenDuration(): Long {
        return when (sharedPreferencesManager.getValue(ALREADY_SHOW_KEY, false)) {
            true -> SHORT_TIME_DURATION
            false -> {
                sharedPreferencesManager.setValue(ALREADY_SHOW_KEY, true)
                LONG_TIME_DURATION
            }
        }
    }

    companion object {
        private const val ALREADY_SHOW_KEY = "splash_already_show"

        private val LONG_TIME_DURATION = TimeUnit.SECONDS.toMillis(3)
        private val SHORT_TIME_DURATION = TimeUnit.SECONDS.toMillis(1)

        fun callingIntent(context: Context): Intent {
            val intent = Intent(context, SplashScreenActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
            return intent
        }
    }
}
