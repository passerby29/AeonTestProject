package dev.passerby.aeon_project.domain.usecases

import dev.passerby.aeon_project.domain.models.LoginDataModel
import dev.passerby.aeon_project.domain.repos.MainRepository

class LoginUseCase(private val repository: MainRepository) {
    suspend operator fun invoke(loginDataModel: LoginDataModel) = repository.login(loginDataModel)
}