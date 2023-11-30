package dev.passerby.aeon_project.domain.repos

import dev.passerby.aeon_project.domain.models.LoginDataModel
import dev.passerby.aeon_project.domain.models.PaymentModel
import dev.passerby.aeon_project.domain.models.TokenModel

interface MainRepository {
    fun removeToken()

    suspend fun login(loginDataModel: LoginDataModel): TokenModel
    suspend fun getPaymentsList(): List<PaymentModel>
}