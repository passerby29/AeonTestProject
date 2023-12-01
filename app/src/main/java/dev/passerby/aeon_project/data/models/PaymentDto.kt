package dev.passerby.aeon_project.data.models


import com.google.gson.annotations.SerializedName

data class PaymentDto(
    @SerializedName("amount")
    val amount: Any?,
    @SerializedName("created")
    val created: Long?,
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String
)