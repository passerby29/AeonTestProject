package dev.passerby.aeon_project.presentation.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dev.passerby.aeon_project.data.repos.MainRepositoryImpl
import dev.passerby.aeon_project.domain.models.PaymentModel
import dev.passerby.aeon_project.domain.usecases.GetPaymentsListUseCase
import dev.passerby.aeon_project.domain.usecases.RemoveTokenUseCase
import kotlinx.coroutines.launch

class PaymentsViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = MainRepositoryImpl(application)

    private val getPaymentsListUseCase = GetPaymentsListUseCase(repository)
    private val removeTokenUseCase = RemoveTokenUseCase(repository)

    private val _payments = MutableLiveData<List<PaymentModel>>()
    val payments: LiveData<List<PaymentModel>>
        get() = _payments

    init {
        getPayments()
    }

    fun removeToken(){
        removeTokenUseCase()
    }

    private fun getPayments() = viewModelScope.launch {
        _payments.value = getPaymentsListUseCase().toList()
    }
}