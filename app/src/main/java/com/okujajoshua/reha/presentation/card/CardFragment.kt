package com.okujajoshua.reha.presentation.card


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders

import com.okujajoshua.reha.R
import com.okujajoshua.reha.databinding.FragmentCardBinding

/**
 * A simple [Fragment] subclass.
 */
class CardFragment : Fragment() {

    private val viewModel:CardViewModel by lazy {
        val activity = requireNotNull(this.activity)

        ViewModelProviders.of(this, CardViewModel.Factory(activity.application))
            .get(CardViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentCardBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_card, container, false)

        // Set the lifecycleOwner so DataBinding can observe LiveData
        binding.setLifecycleOwner(viewLifecycleOwner)

        binding.cardViewModel = viewModel

        return binding.root
    }


}
