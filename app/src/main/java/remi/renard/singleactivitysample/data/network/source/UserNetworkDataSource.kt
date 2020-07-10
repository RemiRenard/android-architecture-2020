package remi.renard.singleactivitysample.data.network.source

import androidx.lifecycle.LiveData
import remi.renard.singleactivitysample.data.network.adapter.ApiResponse
import remi.renard.singleactivitysample.data.network.service.UserService
import remi.renard.singleactivitysample.domain.model.User

class UserNetworkDataSource(private val service: UserService) {

    fun getUsers(since: Int? = null, perPage: Int? = null): LiveData<ApiResponse<List<User>>> =
        service.getUsers()
}
