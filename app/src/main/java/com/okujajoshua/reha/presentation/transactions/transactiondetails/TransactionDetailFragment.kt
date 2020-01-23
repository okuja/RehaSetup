package com.okujajoshua.reha.presentation.transactions.transactiondetails


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController

import com.okujajoshua.reha.R
import com.okujajoshua.reha.database.RehaDatabase
import com.okujajoshua.reha.databinding.FragmentTransactionDetailBinding

/**
 * A simple [Fragment] subclass.
 */
class TransactionDetailFragment : Fragment() {

    private lateinit var transactionDetailViewModel: TransactionDetailViewModel
    private lateinit var transactionDetailViewModelFactory: TransactionDetailViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding:FragmentTransactionDetailBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_transaction_detail, container, false)

        val application = requireNotNull(this.activity).application
        val arguments = TransactionDetailFragmentArgs.fromBundle(arguments!!)

        val dataSource = RehaDatabase.getInstance(application).transactionDao
        transactionDetailViewModelFactory = TransactionDetailViewModelFactory(arguments.transactionId,dataSource)

        transactionDetailViewModel = ViewModelProviders.of(
                this,transactionDetailViewModelFactory).get(TransactionDetailViewModel::class.java)

        binding.transactiondetailViewModel = transactionDetailViewModel

        binding.setLifecycleOwner(this)

        transactionDetailViewModel.navigateToTransactions.observe(this, Observer {
            if (it == true) { // Observed state is true.
                this.findNavController().navigate(
                    TransactionDetailFragmentDirections.actionTransactionDetailFragmentToViewTransactionsFragment())
                // Reset state to make sure we only navigate once, even if the device
                // has a configuration change.
                transactionDetailViewModel.doneNavigating()
            }
        })



        return binding.root
    }


}
