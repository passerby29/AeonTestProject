package dev.passerby.aeon_project.domain.usecases

import dev.passerby.aeon_project.domain.repos.MainRepository

class GetPaymentsListUseCase(private val repository: MainRepository) {
    suspend operator fun invoke() = repository.getPaymentsList()
}