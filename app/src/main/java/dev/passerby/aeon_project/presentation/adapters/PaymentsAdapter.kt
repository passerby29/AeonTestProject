package dev.passerby.aeon_project.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import dev.passerby.aeon_project.databinding.ItemPaymentBinding
import dev.passerby.aeon_project.domain.models.PaymentModel
import dev.passerby.aeon_project.presentation.callbacks.PaymentDiffCallback
import dev.passerby.aeon_project.presentation.viewholders.PaymentViewHolder
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

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
        with(binding) {
            if (item.title.isEmpty()) {
                paymentTitleTextView.text = "Ошибка"
                paymentErrorTextView.visibility = View.VISIBLE
            } else {
                paymentTitleTextView.text = item.title
            }

            if (item.amount == null || item.amount.toString().isEmpty()) {
                paymentAmountTextView.text = "Ошибка"
                paymentErrorTextView.visibility = View.VISIBLE
            } else {
                paymentAmountTextView.text = formatLargeNumber(item.amount)
            }


            if (item.created == null) {
                paymentDateTextView.text = "Ошибка"
                paymentErrorTextView.visibility = View.VISIBLE
            } else {
                paymentDateTextView.text = convertUnixTimestampToDate(item.created)
            }
        }
    }

    private fun convertUnixTimestampToDate(timestamp: Long): String {
        val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault())
        val date = Date(timestamp * 1000)
        return sdf.format(date)
    }

    private fun formatLargeNumber(number: Any): String {
        val numberFormat = NumberFormat.getInstance()
        numberFormat.maximumFractionDigits = 3

        return try {
            when (number) {
                is String -> {
                    val numericValue = number.toDoubleOrNull()
                    if (numericValue != null) {
                        numberFormat.format(numericValue)
                    } else {
                        ""
                    }
                }

                is Long -> numberFormat.format(number)
                is Double -> numberFormat.format(number)
                is Int -> numberFormat.format(number)
                else -> ""
            }
        } catch (e: NumberFormatException) {
            ""
        }
    }
}