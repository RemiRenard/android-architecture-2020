package remi.renard.singleactivitysample.data.network.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Login(
    val email: String,
    val password: String
)
