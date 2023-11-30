package dev.passerby.aeon_project.domain.models

data class TokenResponseModel(
    val token: TokenModel,
    val success: String
)