package dev.passerby.aeon_project.data.models


import com.google.gson.annotations.SerializedName

data class TokenDto(
    @SerializedName("token")
    val token: String
)