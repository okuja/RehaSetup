package com.okujajoshua.reha.presentation.transactions.addmoney


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.okujajoshua.reha.R

/**
 * A simple [Fragment] subclass.
 */
class AddMoneyFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_money, container, false)
    }


}
