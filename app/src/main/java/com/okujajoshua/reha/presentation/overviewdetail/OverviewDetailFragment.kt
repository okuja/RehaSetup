package com.okujajoshua.reha.presentation.overviewdetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.okujajoshua.reha.databinding.OverViewDetailFragmentBinding


/**
 * This [Fragment] will show the detailed information about a selected piece of Mars real estate.
 */
class OverviewDetailFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        @Suppress("UNUSED_VARIABLE")
        val application = requireNotNull(activity).application
        val binding = OverViewDetailFragmentBinding.inflate(inflater)
        binding.setLifecycleOwner(this)
        return binding.root
    }
}
