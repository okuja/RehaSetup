package com.okujajoshua.reha.presentation.card


import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.okujajoshua.reha.R
import com.okujajoshua.reha.databinding.FragmentCardBinding
import com.okujajoshua.reha.domain.DomainCard

/**
 * A simple [Fragment] subclass.
 */
class CardFragment : Fragment() {

    private val viewModel: CardViewModel by lazy {
        val activity = requireNotNull(this.activity)

        val args = CardFragmentArgs.fromBundle(arguments!!)

        ViewModelProviders.of(this, CardViewModel.Factory(activity.application,args.email))
            .get(CardViewModel::class.java)
    }

    private var viewModelAdapter: CardAdapter? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.cards.observe(viewLifecycleOwner, Observer<List<DomainCard>> { cards ->
            cards?.apply {
                viewModelAdapter?.cards = cards
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentCardBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_card, container, false)

        // Set the lifecycleOwner so DataBinding can observe LiveData
        binding.setLifecycleOwner(viewLifecycleOwner)

        binding.cardViewModel = viewModel

        viewModelAdapter = CardAdapter(CardClick {
            // When a card is clicked this block or lambda will be called

            this.findNavController().navigate(
                CardFragmentDirections.actionCardFragmentToBalanceFragment(it.cardId)
            )

        })

        binding.root.findViewById<RecyclerView>(R.id.cards_list).apply {
            layoutManager = LinearLayoutManager(context)
            adapter = viewModelAdapter
        }

        binding.verifyAccountButton.setOnClickListener{view: View ->
            view.findNavController().navigate(
                CardFragmentDirections.actionCardFragmentToVerificationFragment()
            )
        }


        // Observer for the network error.
        viewModel.eventNetworkError.observe(this, Observer<Boolean> { isNetworkError ->
            if (isNetworkError) onNetworkError()
        })

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.options_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item!!.itemId) {
            R.id.viewProfileFragment ->  view!!.findNavController().navigate(R.id.action_cardFragment_to_viewProfileFragment)
        }
        return super.onOptionsItemSelected(item)
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

