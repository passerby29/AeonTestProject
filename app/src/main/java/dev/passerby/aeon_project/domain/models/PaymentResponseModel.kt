package dev.passerby.aeon_project.domain.models

data class PaymentResponseModel(
    val payments: List<PaymentModel>,
    val success: String
)