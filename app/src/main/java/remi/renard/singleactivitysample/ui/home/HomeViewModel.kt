package remi.renard.singleactivitysample.ui.home

import androidx.lifecycle.ViewModel
import javax.inject.Inject

class HomeViewModel @Inject constructor() : ViewModel() {

    fun getMessageFromHomeActivity(): String {
        return "This message is from the viewModel of the HomeActivity"
    }
}
