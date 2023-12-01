package dev.passerby.aeon_project.presentation.callbacks

import androidx.recyclerview.widget.DiffUtil
import dev.passerby.aeon_project.domain.models.PaymentModel

class PaymentDiffCallback:DiffUtil.ItemCallback<PaymentModel>() {
    override fun areItemsTheSame(oldItem: PaymentModel, newItem: PaymentModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: PaymentModel, newItem: PaymentModel): Boolean {
        return oldItem == newItem
    }
}