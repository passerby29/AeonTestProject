package dev.passerby.aeon_project.domain.repos

import dev.passerby.aeon_project.domain.models.LoginDataModel
import dev.passerby.aeon_project.domain.models.PaymentModel
import dev.passerby.aeon_project.domain.models.TokenModel
import dev.passerby.aeon_project.domain.models.TokenResponseModel

interface MainRepository {
    fun removeToken()

    fun isTokenAdded(): Boolean

    suspend fun login(loginDataModel: LoginDataModel): TokenResponseModel
    suspend fun getPaymentsList(): List<PaymentModel>
}