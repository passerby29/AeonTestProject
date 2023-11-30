package dev.passerby.aeon_project.data.models


import com.google.gson.annotations.SerializedName

data class TokenResponseDto(
    @SerializedName("response")
    val token: TokenDto,
    @SerializedName("success")
    val success: String
)