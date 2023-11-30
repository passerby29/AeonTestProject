package dev.passerby.aeon_project.data.mappers

import dev.passerby.aeon_project.data.models.PaymentDto
import dev.passerby.aeon_project.domain.models.PaymentModel

class PaymentsMapper {
    fun mapDtoListToEntityList(dtoList: List<PaymentDto>): List<PaymentModel> {
        return dtoList.map {
            mapPaymentDtoToEntity(it)
        }
    }

    private fun mapPaymentDtoToEntity(dto: PaymentDto) = PaymentModel(
        amount = dto.amount,
        created = dto.created,
        id = dto.id,
        title = dto.title,
    )
}