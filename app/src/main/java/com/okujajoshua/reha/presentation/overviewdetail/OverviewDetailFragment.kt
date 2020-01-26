package com.okujajoshua.reha.presentation.overviewdetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.okujajoshua.reha.databinding.OverViewDetailFragmentBinding


/**
 * This [Fragment] will show the detailed information about a selected piece of Mars real estate.
 */
class OverviewDetailFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val application = requireNotNull(activity).application

        val binding = OverViewDetailFragmentBinding.inflate(inflater)

        binding.setLifecycleOwner(this)

        val marsProperty = OverviewDetailFragmentArgs.fromBundle(arguments!!).selectedProperty

        val viewModelFactory = OverviewDetailModelViewFactory(marsProperty, application)

        binding.viewModel = ViewModelProviders.of(
            this, viewModelFactory).get(OverviewDetailViewModel::class.java)

        return binding.root
    }
}
