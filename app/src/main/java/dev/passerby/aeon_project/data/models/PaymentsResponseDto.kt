package dev.passerby.aeon_project.data.models


import com.google.gson.annotations.SerializedName

data class PaymentsResponseDto(
    @SerializedName("response")
    val payments: List<PaymentDto>,
    @SerializedName("success")
    val success: String
)