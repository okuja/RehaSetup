package com.okujajoshua.reha.presentation.transactions.viewtransactions

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.okujajoshua.reha.database.Transaction
import com.okujajoshua.reha.databinding.TransactionItemBinding

class TransactionsAdapter(val clickListener: TransactionsListener) :
    ListAdapter<Transaction, TransactionsAdapter.ViewHolder>(TransactionDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position)!!, clickListener)
    }


    class ViewHolder private constructor(val binding: TransactionItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            item: Transaction,
            clickListener: TransactionsListener
        ) {
            //format the data to display from here
            binding.amount.text = item.amount
            binding.email.text = item.email
            binding.transaction = item
            binding.clickListener = clickListener
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = TransactionItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }


}


class TransactionsListener(val clickListener: (transactionId: Int) -> Unit) {
    fun onClick(transaction: Transaction) = clickListener(transaction.id!!)
}

//implementing diffutil
class TransactionDiffCallback : DiffUtil.ItemCallback<Transaction>() {
    override fun areItemsTheSame(oldItem: Transaction, newItem: Transaction): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Transaction, newItem: Transaction): Boolean {
        return oldItem == newItem
    }
}
