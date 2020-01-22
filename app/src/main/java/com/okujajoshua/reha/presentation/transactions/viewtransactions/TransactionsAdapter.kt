package com.okujajoshua.reha.presentation.transactions.viewtransactions

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.okujajoshua.reha.R
import com.okujajoshua.reha.database.Transaction
import com.okujajoshua.reha.databinding.TransactionItemBinding

class TransactionsAdapter : ListAdapter<Transaction, TransactionsAdapter.ViewHolder>(TransactionDiffCallback()) {

    //implementing diffutil
    class TransactionDiffCallback : DiffUtil.ItemCallback<Transaction>(){
        override fun areItemsTheSame(oldItem: Transaction, newItem: Transaction): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Transaction, newItem: Transaction): Boolean {
           return oldItem == newItem
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }



    class ViewHolder private constructor(binding: TransactionItemBinding) : RecyclerView.ViewHolder(binding.root){
        val amount : TextView = binding.amount
        val email : TextView = binding.email

        fun bind(
            item: Transaction
        ) {
            //format the data to display from here
            amount.text = item.amount
            email.text = item.email
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