package com.okujajoshua.reha.presentation.transactions.viewtransactions


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager

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

        binding.viewtransactionsViewModel = viewTransactionsViewModel

        val adapter =TransactionsAdapter(TransactionsListener { transactionId ->
//            Toast.makeText(context, "${transactionId}", Toast.LENGTH_LONG).show()
            viewTransactionsViewModel.onTransactionClicked(transactionId)
        })

        binding.transactionsList.adapter = adapter

        viewTransactionsViewModel.transactions.observe(viewLifecycleOwner, Observer {
            it?.let{
                adapter.submitList(it)
            }
        })

        binding.setLifecycleOwner (this)

        viewTransactionsViewModel.navigateToTransactionDetail.observe(this, Observer {transaction ->
            transaction ?.let{
                this.findNavController().navigate(
                    ViewTransactionsFragmentDirections
                        .actionViewTransactionsFragmentToTransactionDetailFragment(transaction)
                )
                viewTransactionsViewModel.onDoneNavigatingToTransactionDetail()
            }
        })

//
//        val layoutManager = GridLayoutManager(activity,2)
//        binding.transactionsList.layoutManager = layoutManager

        return binding.root
    }


}
