package remi.renard.singleactivitysample.ui.home.users

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import remi.renard.singleactivitysample.domain.interactor.Resource
import remi.renard.singleactivitysample.domain.model.User
import remi.renard.singleactivitysample.domain.repository.UserRepository
import javax.inject.Inject

class UsersViewModel @Inject constructor(userRepository: UserRepository) : ViewModel() {

    private val _users = MutableLiveData<Boolean>()
    val users: LiveData<Resource<List<User>>> =
        _users.switchMap { userRepository.getUsers() }

    fun getUsers() {
        _users.value = true
    }
}
