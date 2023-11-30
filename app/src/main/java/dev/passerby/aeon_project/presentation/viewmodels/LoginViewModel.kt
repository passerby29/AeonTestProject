package dev.passerby.aeon_project.presentation.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dev.passerby.aeon_project.data.repos.MainRepositoryImpl
import dev.passerby.aeon_project.domain.models.LoginDataModel
import dev.passerby.aeon_project.domain.usecases.GetPaymentsListUseCase
import dev.passerby.aeon_project.domain.usecases.LoginUseCase
import dev.passerby.aeon_project.domain.usecases.RemoveTokenUseCase
import kotlinx.coroutines.launch

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = MainRepositoryImpl(application)

    private val getPaymentsListUseCase = GetPaymentsListUseCase(repository)
    private val loginUseCase = LoginUseCase(repository)
    private val removeTokenUseCase = RemoveTokenUseCase(repository)

    private val _tokenSuccess = MutableLiveData<String>()
    val tokenSuccess : LiveData<String>
        get() = _tokenSuccess

    fun login(loginDataModel: LoginDataModel) {
        viewModelScope.launch {
            val loginData = loginUseCase(loginDataModel)
            _tokenSuccess.value = loginData.success
        }
    }
}