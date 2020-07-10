package remi.renard.singleactivitysample.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import remi.renard.singleactivitysample.R
import remi.renard.singleactivitysample.di.Injectable
import remi.renard.singleactivitysample.ui.BaseActivity
import remi.renard.singleactivitysample.ui.home.users.UsersFragment
import javax.inject.Inject

class HomeActivity : BaseActivity(R.layout.activity_home), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<HomeViewModel> { viewModelFactory }

    private lateinit var title: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindViews()
        supportFragmentManager.beginTransaction()
            .replace(R.id.home_fragment_container, UsersFragment.newInstance())
            .commit()
    }

    private fun bindViews() {
        title = findViewById(R.id.home_message_title)
    }

    companion object {
        fun callingIntent(context: Context): Intent {
            return Intent(context, HomeActivity::class.java)
        }
    }
}