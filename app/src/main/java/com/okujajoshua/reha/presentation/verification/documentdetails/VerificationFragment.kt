package com.okujajoshua.reha.presentation.verification.documentdetails


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController

import com.okujajoshua.reha.R
import com.okujajoshua.reha.databinding.FragmentVerificationBinding

/**
 * A simple [Fragment] subclass.
 */
class VerificationFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentVerificationBinding =
            DataBindingUtil.inflate(inflater,R.layout.fragment_verification, container, false)

        binding.signinButton.setOnClickListener { view: View ->
            view.findNavController().navigate(
                VerificationFragmentDirections.actionVerificationFragmentToIdentificationFragment()
            )
        }

        return binding.root
    }


}
