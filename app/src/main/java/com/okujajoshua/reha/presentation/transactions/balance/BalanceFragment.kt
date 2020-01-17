package com.okujajoshua.reha.presentation.transactions.balance


import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
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
        // Inflate the layout for this fragment
        val binding : FragmentBalanceBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_balance,container, false)


        //from navigation argument bundle
        val args = BalanceFragmentArgs.fromBundle(arguments!!)
        Toast.makeText(context, "Email: ${args.email}, Password: ${args.password}", Toast.LENGTH_LONG).show()


        //initialize viewmodelfactory
        viewModelFactory = BalanceViewModelFactory(args.email,args.password)

        //


        binding.addmoneyButton.setOnClickListener{view: View ->
            view.findNavController().navigate(R.id.action_balanceFragment_to_addMoneyFragment)
        }

        binding.sendmoneyButton.setOnClickListener{view: View ->
            view.findNavController().navigate(R.id.action_balanceFragment_to_sendMoneyFragment)
        }

        binding.verifyaccountButton.setOnClickListener{view: View ->
            view.findNavController().navigate(R.id.action_balanceFragment_to_verificationFragment)
        }

        setHasOptionsMenu(true)


        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.options_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item!!.itemId) {
            R.id.viewProfileFragment ->  view!!.findNavController().navigate(R.id.action_balanceFragment_to_viewProfileFragment)
        }
        return super.onOptionsItemSelected(item)

        //return NavigationUI.onNavDestinationSelected(item,view!!.findNavController()) || super.onOptionsItemSelected(item)
    }


}
