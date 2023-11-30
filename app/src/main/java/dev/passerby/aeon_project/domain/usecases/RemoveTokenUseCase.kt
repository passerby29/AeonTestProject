package dev.passerby.aeon_project.domain.usecases

import dev.passerby.aeon_project.domain.repos.MainRepository

class RemoveTokenUseCase(private val repository: MainRepository) {
    operator fun invoke() = repository.removeToken()
}