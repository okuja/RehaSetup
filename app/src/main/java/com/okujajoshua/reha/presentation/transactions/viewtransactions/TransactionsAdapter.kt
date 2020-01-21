package com.okujajoshua.reha.presentation.transactions.viewtransactions

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.okujajoshua.reha.R
import com.okujajoshua.reha.database.Transaction
import com.okujajoshua.reha.util.TransactionItemViewHolder

class TransactionsAdapter : RecyclerView.Adapter<TransactionItemViewHolder>() {

    var data = listOf<Transaction>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.transaction_item,parent,false) as TextView
        return TransactionItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: TransactionItemViewHolder, position: Int) {
        val item = data[position]

        holder.textView.text = item.amount
    }


    override fun getItemCount() = data.size
}