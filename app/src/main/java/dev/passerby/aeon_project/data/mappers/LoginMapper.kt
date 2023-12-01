package dev.passerby.aeon_project.data.mappers

import dev.passerby.aeon_project.data.models.LoginDataDto
import dev.passerby.aeon_project.data.models.TokenDto
import dev.passerby.aeon_project.data.models.TokenResponseDto
import dev.passerby.aeon_project.domain.models.LoginDataModel
import dev.passerby.aeon_project.domain.models.TokenModel
import dev.passerby.aeon_project.domain.models.TokenResponseModel

class LoginMapper {

    fun mapResponseDtoToEntity(dto: TokenResponseDto) = TokenResponseModel(
        token = mapTokenDtoToEntity(dto.token),
        success = dto.success
    )

    private fun mapTokenDtoToEntity(dto: TokenDto) = TokenModel(
        token = dto.token
    )

    fun mapLoginModelToDto(loginDataModel: LoginDataModel) = LoginDataDto(
        login = loginDataModel.login,
        password = loginDataModel.password,
    )
}