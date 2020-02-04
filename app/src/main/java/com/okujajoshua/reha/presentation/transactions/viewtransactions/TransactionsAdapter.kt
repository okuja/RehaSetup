package com.okujajoshua.reha.presentation.transactions.viewtransactions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.okujajoshua.reha.R
import com.okujajoshua.reha.database.transaction.Transaction
import com.okujajoshua.reha.databinding.TransactionItemBinding


//private val ITEM_VIEW_TYPE_HEADER = 0
//private val ITEM_VIEW_TYPE_ITEM = 1

class TransactionsAdapter(val clickListener: TransactionsListener) :
    ListAdapter<Transaction, TransactionsAdapter.ViewHolder>(TransactionDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position)!!, clickListener)
    }

//    override fun getItemViewType(position: Int): Int {
//        return when (getItem(position)) {
//            is DataItem.Header -> ITEM_VIEW_TYPE_HEADER
//            is DataItem.TransactionItem -> ITEM_VIEW_TYPE_ITEM
//        }
//    }

    class TextViewHolder(view: View): RecyclerView.ViewHolder(view) {
        companion object {
            fun from(parent: ViewGroup): TextViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.header, parent, false)
                return TextViewHolder(view)
            }
        }
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

//sealed class DataItem{
//
//    abstract val id:Int?
//
//    data class TransactionItem(val transaction : Transaction) : DataItem(){
//        override val id = transaction.id
//    }
//
//    object Header : DataItem(){
//        override val id = Int.MIN_VALUE
//    }
//
//}
