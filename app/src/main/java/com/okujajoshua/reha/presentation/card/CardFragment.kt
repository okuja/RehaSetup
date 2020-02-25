package com.okujajoshua.reha.presentation.card


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

        ViewModelProviders.of(this, CardViewModel.Factory(activity.application))
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
                CardFragmentDirections.actionCardFragmentToCardDetailFragment(it.cardId)
            )

        })

        binding.root.findViewById<RecyclerView>(R.id.cards_list).apply {
            layoutManager = LinearLayoutManager(context)
            adapter = viewModelAdapter
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

