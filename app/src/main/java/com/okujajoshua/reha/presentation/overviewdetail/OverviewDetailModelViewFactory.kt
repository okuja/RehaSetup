package com.okujajoshua.reha.presentation.overviewdetail

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.okujajoshua.reha.network.MarsProperty


/**
 * Simple ViewModel factory that provides the MarsProperty and context to the ViewModel.
 */
class OverviewDetailModelViewFactory(
    private val marsProperty: MarsProperty,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(OverviewDetailViewModel::class.java)) {
            return OverviewDetailViewModel(marsProperty, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}