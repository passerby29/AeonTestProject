package dev.passerby.aeon_project.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import dev.passerby.aeon_project.databinding.ItemPaymentBinding
import dev.passerby.aeon_project.domain.models.PaymentModel
import dev.passerby.aeon_project.presentation.callbacks.PaymentDiffCallback
import dev.passerby.aeon_project.presentation.viewholders.PaymentViewHolder

class PaymentsAdapter : ListAdapter<PaymentModel, PaymentViewHolder>(PaymentDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaymentViewHolder {
        val itemView = ItemPaymentBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PaymentViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PaymentViewHolder, position: Int) {
        val item = getItem(position)
        val binding = holder.binding
        with(binding){
            paymentTitle.text = item.title
            paymentAmount.text = item.amount.toString()
            paymentCreated.text = item.created.toString()
        }
    }
}