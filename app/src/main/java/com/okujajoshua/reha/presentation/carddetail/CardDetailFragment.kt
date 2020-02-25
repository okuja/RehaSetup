package com.okujajoshua.reha.presentation.carddetail


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders

import com.okujajoshua.reha.R
import com.okujajoshua.reha.databinding.FragmentCardDetailBinding

/**
 * A simple [Fragment] subclass.
 */
class CardDetailFragment : Fragment() {

    private val viewModel: CardDetailViewModel by lazy {
        ViewModelProviders.of(this).get(CardDetailViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val application = requireNotNull(activity).application

        // Inflate the layout for this fragment
        val binding: FragmentCardDetailBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_card_detail, container, false)

        binding.setLifecycleOwner(this)

        val cardid = CardDetailFragmentArgs.fromBundle(arguments!!).cardId

        val viewModelFactory = CardDetailViewModelFactory(cardid,application)

        binding.viewModel = ViewModelProviders.of(
            this, viewModelFactory).get(CardDetailViewModel::class.java)

        return binding.root
    }


}
