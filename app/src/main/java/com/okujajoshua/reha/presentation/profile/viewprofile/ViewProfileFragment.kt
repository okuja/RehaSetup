package com.okujajoshua.reha.presentation.profile.viewprofile


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders

import com.okujajoshua.reha.R
import com.okujajoshua.reha.database.RehaDatabase
import com.okujajoshua.reha.databinding.FragmentViewProfileBinding

/**
 * A simple [Fragment] subclass.
 */
class ViewProfileFragment : Fragment() {

    private lateinit var viewProfileViewModel: ViewProfileViewModel
    private lateinit var viewProfileViewModelFactory: ViewProfileViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding : FragmentViewProfileBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_view_profile, container, false)

        val application = requireNotNull(this.activity).application

        val dataSource = RehaDatabase.getInstance(application).rehaDatabaseDao

        val userEmail = "ajokuja@gmail.com"

        viewProfileViewModelFactory = ViewProfileViewModelFactory(dataSource, application,userEmail)

        viewProfileViewModel = ViewModelProviders.of(this,viewProfileViewModelFactory).get(ViewProfileViewModel::class.java)

        binding.setLifecycleOwner (this)

        binding.viewProfileViewModel = viewProfileViewModel

        return binding.root
    }


}
