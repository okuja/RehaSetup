package com.okujajoshua.reha.presentation.transactions.balance


import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI

import com.okujajoshua.reha.R
import com.okujajoshua.reha.databinding.FragmentBalanceBinding

/**
 * A simple [Fragment] subclass.
 */
class BalanceFragment : Fragment() {

    private lateinit var viewModel: BalanceViewModel
    private lateinit var viewModelFactory: BalanceViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val application = requireNotNull(activity).application

        // Inflate the layout for this fragment
        val binding : FragmentBalanceBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_balance,container, false)

        //from navigation argument bundle
        val args = BalanceFragmentArgs.fromBundle(arguments!!)

        //initialize viewmodelfactory
        viewModelFactory = BalanceViewModelFactory(args.cardId,application)

        //initialize viewmodel object
        viewModel = ViewModelProviders.of(this,viewModelFactory).get(BalanceViewModel::class.java)

        // Set the viewmodel for databinding - this allows the bound layout access
        // to all the data in the ViewModel
        binding.balanceViewModel = viewModel

        // Specify the current activity as the lifecycle owner of the binding.
        // This is used so that the binding can observe LiveData updates
        binding.lifecycleOwner = this

        binding.addmoneyButton.setOnClickListener{view: View ->
            view.findNavController().navigate(
                BalanceFragmentDirections.actionBalanceFragmentToOptionsFragment()
            )
        }

        binding.sendmoneyButton.setOnClickListener{view: View ->
            view.findNavController().navigate(
               BalanceFragmentDirections.actionBalanceFragmentToAddMoneyFragment(args.cardId,"Send Money")
            )
        }

        binding.viewTransactions.setOnClickListener { view: View ->
            view.findNavController().navigate(
                BalanceFragmentDirections.actionBalanceFragmentToViewTransactionsFragment()
            )
        }

        // Observer for the network error.
        viewModel.eventNetworkError.observe(this, Observer<Boolean> { isNetworkError ->
            if (isNetworkError) onNetworkError()
        })


        return binding.root
    }

    /**
     * Method for displaying a Toast error message for network errors.
     */
    private fun onNetworkError() {
        if(!viewModel.isNetworkErrorShown.value!!) {
            Toast.makeText(activity, "Network Error", Toast.LENGTH_LONG).show()
            viewModel.onNetworkErrorShown()
        }
    }
}
