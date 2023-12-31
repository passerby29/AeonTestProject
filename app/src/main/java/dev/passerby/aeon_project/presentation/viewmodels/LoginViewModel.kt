package dev.passerby.aeon_project.presentation.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dev.passerby.aeon_project.data.repos.MainRepositoryImpl
import dev.passerby.aeon_project.domain.models.LoginDataModel
import dev.passerby.aeon_project.domain.usecases.IsTokenAddedUseCase
import dev.passerby.aeon_project.domain.usecases.LoginUseCase
import kotlinx.coroutines.launch

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = MainRepositoryImpl(application)

    private val isTokenAddedUseCase = IsTokenAddedUseCase(repository)
    private val loginUseCase = LoginUseCase(repository)

    private val _tokenSuccess = MutableLiveData<String>()
    val tokenSuccess: LiveData<String>
        get() = _tokenSuccess

    val isTokenAdded = isTokenAddedUseCase()

    fun login(loginDataModel: LoginDataModel) = viewModelScope.launch {
        val loginData = loginUseCase(loginDataModel)
        _tokenSuccess.value = loginData.success
    }

    fun resetToken() {
        _tokenSuccess.value = ""
    }
}