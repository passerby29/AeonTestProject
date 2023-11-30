package dev.passerby.aeon_project.data.network

import dev.passerby.aeon_project.data.models.LoginDataDto
import dev.passerby.aeon_project.data.models.PaymentsResponseDto
import dev.passerby.aeon_project.data.models.TokenResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {

    @Headers(
        "app-key: 12345",
        "v: 1"
    )
    @POST("login")
    suspend fun getLoginToken(@Body body: LoginDataDto): Response<TokenResponseDto>

    @GET("payments")
    suspend fun getPaymentsList(@HeaderMap headers: Map<String, String>): Response<PaymentsResponseDto>
}