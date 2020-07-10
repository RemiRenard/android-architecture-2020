package remi.renard.singleactivitysample.data.network.service

import androidx.lifecycle.LiveData
import remi.renard.singleactivitysample.data.network.ServiceFactory.Companion.BASE_URL
import remi.renard.singleactivitysample.data.network.adapter.ApiResponse
import remi.renard.singleactivitysample.domain.model.User
import retrofit2.http.GET
import retrofit2.http.Query

interface UserService {

    @GET(BASE_URL + "users")
    fun getUsers(
        @Query("since") since: Int? = null,
        @Query("per_page") perPage: Int? = null
    ): LiveData<ApiResponse<List<User>>>
}
