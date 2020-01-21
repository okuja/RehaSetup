package com.okujajoshua.reha.presentation.transactions.addmoney


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders

import com.okujajoshua.reha.R
import com.okujajoshua.reha.database.RehaDatabase
import com.okujajoshua.reha.databinding.FragmentAddMoneyBinding

/**
 * A simple [Fragment] subclass.
 */
class AddMoneyFragment : Fragment() {

    private lateinit var addMoneyViewModel: AddMoneyViewModel
    private lateinit var addMoneyViewModelFactory: AddMoneyViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding : FragmentAddMoneyBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_add_money, container, false)

        //from navigation argument bundle
        val args = AddMoneyFragmentArgs.fromBundle(arguments!!)

        val application = requireNotNull(this.activity).application

        val dataSource = RehaDatabase.getInstance(application).transactionDao

        addMoneyViewModelFactory = AddMoneyViewModelFactory(args.email,dataSource)

        addMoneyViewModel = ViewModelProviders.of(this,addMoneyViewModelFactory).get(AddMoneyViewModel::class.java)

        binding.setLifecycleOwner (this)

        binding.addMoneyViewModel = addMoneyViewModel

        return binding.root
    }


}
