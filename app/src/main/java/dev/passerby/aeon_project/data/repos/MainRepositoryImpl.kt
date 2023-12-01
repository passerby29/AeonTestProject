package dev.passerby.aeon_project.data.repos

import android.app.Application
import android.content.Context.MODE_PRIVATE
import android.util.Log
import androidx.lifecycle.MutableLiveData
import dev.passerby.aeon_project.data.Constants.APP_KEY
import dev.passerby.aeon_project.data.Constants.PREF_NAME
import dev.passerby.aeon_project.data.Constants.TOKEN_KEY
import dev.passerby.aeon_project.data.Constants.VERSION
import dev.passerby.aeon_project.data.mappers.LoginMapper
import dev.passerby.aeon_project.data.mappers.PaymentsMapper
import dev.passerby.aeon_project.data.models.PaymentsResponseDto
import dev.passerby.aeon_project.data.models.TokenResponseDto
import dev.passerby.aeon_project.data.network.ApiFactory
import dev.passerby.aeon_project.data.network.BaseResponse
import dev.passerby.aeon_project.domain.models.LoginDataModel
import dev.passerby.aeon_project.domain.models.PaymentModel
import dev.passerby.aeon_project.domain.models.TokenModel
import dev.passerby.aeon_project.domain.models.TokenResponseModel
import dev.passerby.aeon_project.domain.repos.MainRepository

class MainRepositoryImpl(application: Application) : MainRepository {

    private val sharedPreferences = application.getSharedPreferences(PREF_NAME, MODE_PRIVATE)
    private val editor = sharedPreferences.edit()

    private val apiService = ApiFactory.apiService

    private val loginMapper = LoginMapper()
    private val paymentsMapper = PaymentsMapper()

    private val loginResult: MutableLiveData<BaseResponse<TokenResponseDto>> = MutableLiveData()
    private val paymentsResult: MutableLiveData<BaseResponse<PaymentsResponseDto>> =
        MutableLiveData()

    override fun removeToken() {
        editor.putString(TOKEN_KEY, "").apply()
    }

    override fun isTokenAdded(): Boolean {
        val token = sharedPreferences.getString(TOKEN_KEY, "")
        return !token.isNullOrEmpty()
    }

    override suspend fun login(loginDataModel: LoginDataModel): TokenResponseModel {

        loginResult.postValue(BaseResponse.Loading())

        try {
            val response = apiService.getLoginToken(
                loginMapper.mapLoginModelToDto(loginDataModel)
            )
            if (response.code() == 200) {
                loginResult.postValue(BaseResponse.Success(response.body()))

                editor.putString(TOKEN_KEY, response.body()!!.token.token).apply()

                Log.d(TAG, "loginTry: ${response.isSuccessful}")
                return loginMapper.mapResponseDtoToEntity(response.body()!!)
            } else {
                loginResult.postValue(BaseResponse.Error(response.message()))
                Log.d(TAG, "loginElse: ${response.message()}")
            }
        } catch (ex: Exception) {
            loginResult.postValue(BaseResponse.Error(ex.message))
            Log.d(TAG, "loginCatch: $ex")
        }
        return TokenResponseModel(TokenModel(""), "false")
    }

    override suspend fun getPaymentsList(): List<PaymentModel> {
        val tokenHeader = HashMap<String, String>()
        tokenHeader[APP_KEY] = "12345"
        tokenHeader[VERSION] = "1"
        tokenHeader[TOKEN_KEY] = sharedPreferences.getString(TOKEN_KEY, "").toString()

        paymentsResult.postValue(BaseResponse.Loading())
        try {
            val response = apiService.getPaymentsList(tokenHeader)
            if (response.code() == 200) {
                paymentsResult.postValue(BaseResponse.Success(response.body()))
                Log.d(TAG, "getPaymentsListTry: ${response.isSuccessful}")
                return paymentsMapper.mapDtoListToEntityList(response.body()!!.payments)
            } else {
                paymentsResult.postValue(BaseResponse.Error(response.message()))
                Log.d(TAG, "getPaymentsListElse: ${response.message()}")
            }
        } catch (ex: Exception) {
            paymentsResult.postValue(BaseResponse.Error(ex.message))
            Log.d(TAG, "getPaymentsListCatch: $ex")
        }
        return emptyList()
    }

    companion object {
        private const val TAG = "MainRepositoryImplTAG"
    }
}