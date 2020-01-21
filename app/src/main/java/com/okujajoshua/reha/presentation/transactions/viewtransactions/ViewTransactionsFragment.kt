package com.okujajoshua.reha.presentation.transactions.viewtransactions


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import com.okujajoshua.reha.R
import com.okujajoshua.reha.database.RehaDatabase
import com.okujajoshua.reha.databinding.FragmentViewTransactionsBinding

/**
 * A simple [Fragment] subclass.
 */
class ViewTransactionsFragment : Fragment() {

    private lateinit var viewTransactionsViewModel: ViewTransactionsViewModel
    private lateinit var viewTransactionsViewModelFactoy: ViewTransactionsViewModelFactoy

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentViewTransactionsBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_view_transactions, container, false)

        val application = requireNotNull(this.activity).application
        val dataSource = RehaDatabase.getInstance(application).transactionDao

        viewTransactionsViewModelFactoy = ViewTransactionsViewModelFactoy("ajokuja@gmail.com",dataSource)
        viewTransactionsViewModel = ViewModelProviders.of(this,viewTransactionsViewModelFactoy).get(ViewTransactionsViewModel::class.java)

        val adapter =TransactionsAdapter()
        binding.transactionsList.adapter = adapter
        viewTransactionsViewModel.transactions.observe(viewLifecycleOwner, Observer {
            it?.let{
                adapter.data = it
            }
        })

        binding.setLifecycleOwner (this)

        binding.viewtransactionsViewModel = viewTransactionsViewModel

        return binding.root
    }


}
