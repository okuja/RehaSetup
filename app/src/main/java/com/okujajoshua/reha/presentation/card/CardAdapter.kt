package com.okujajoshua.reha.presentation.card

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.okujajoshua.reha.R
import com.okujajoshua.reha.databinding.CardItemBinding
import com.okujajoshua.reha.domain.DomainCard

class CardAdapter(val callback : CardClick) : RecyclerView.Adapter<CardViewHolder>() {
    var cards : List<DomainCard> = emptyList()
    set(value){
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val withDataBinding:CardItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            CardViewHolder.LAYOUT,
            parent,
            false)
        return CardViewHolder(withDataBinding)
    }

    override fun getItemCount() = cards.size

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.card = cards[position]
            it.cardCallBack = callback
        }
    }
}

class CardViewHolder(val viewDataBinding: CardItemBinding) : RecyclerView.ViewHolder(viewDataBinding.root){
    companion object{
        @LayoutRes
        val LAYOUT = R.layout.card_item
    }
}

class CardClick(val block : (DomainCard)->Unit){
    fun onClick(card: DomainCard) = block(card)
}