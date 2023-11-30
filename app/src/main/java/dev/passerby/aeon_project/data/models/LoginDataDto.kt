package dev.passerby.aeon_project.data.models


import com.google.gson.annotations.SerializedName

data class LoginDataDto(
    @SerializedName("login")
    val login: String,
    @SerializedName("password")
    val password: String
)