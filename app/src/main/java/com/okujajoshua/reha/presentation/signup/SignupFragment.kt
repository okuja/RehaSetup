package com.okujajoshua.reha.presentation.signup


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders

import com.okujajoshua.reha.R
import com.okujajoshua.reha.database.RehaDatabase
import com.okujajoshua.reha.databinding.FragmentSignupBinding

/**
 * A simple [Fragment] subclass.
 */
class SignupFragment : Fragment() {

    private lateinit var signUpViewModel: SignUpViewModel
    private lateinit var signUpViewModelFactory: SignUpViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding : FragmentSignupBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_signup, container, false)

        val application = requireNotNull(this.activity).application

        val dataSource = RehaDatabase.getInstance(application).rehaDatabaseDao

        signUpViewModelFactory = SignUpViewModelFactory(dataSource,application)

        signUpViewModel = ViewModelProviders.of(this,signUpViewModelFactory).get(SignUpViewModel::class.java)

        binding.setLifecycleOwner (this)

        binding.signupViewModel = signUpViewModel

        return binding.root
    }


}
