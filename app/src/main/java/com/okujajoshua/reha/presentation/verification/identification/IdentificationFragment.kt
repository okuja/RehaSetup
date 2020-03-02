package com.okujajoshua.reha.presentation.verification.identification


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController

import com.okujajoshua.reha.R
import com.okujajoshua.reha.databinding.FragmentIdentificationBinding

/**
 * A simple [Fragment] subclass.
 */
class IdentificationFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentIdentificationBinding =
            DataBindingUtil.inflate(inflater,R.layout.fragment_identification, container, false)

        //TODO:Handle onclick for camera

        binding.continueButton.setOnClickListener { view:View->
            view.findNavController().navigate(
                IdentificationFragmentDirections.actionIdentificationFragmentToSelfieFragment()
            )
        }

        return binding.root
    }


}
