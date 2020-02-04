package com.okujajoshua.reha.presentation.transactions.options


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController

import com.okujajoshua.reha.R
import com.okujajoshua.reha.databinding.FragmentOptionsBinding

/**
 * A simple [Fragment] subclass.
 */
class OptionsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding : FragmentOptionsBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_options, container, false)

        binding.cardPaymentButton.setOnClickListener{view : View ->
            view.findNavController().navigate(
                OptionsFragmentDirections.actionOptionsFragmentToAddMoneyFragment2("email","Add Money")
            )
        }

        binding.mobilePaymentButton.setOnClickListener{view : View ->
            view.findNavController().navigate(
                OptionsFragmentDirections.actionOptionsFragmentToOptionPhoneFragment()
            )

        }

        return binding.root
    }


}
