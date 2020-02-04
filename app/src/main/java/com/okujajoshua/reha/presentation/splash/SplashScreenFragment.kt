package com.okujajoshua.reha.presentation.splash


import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController

import com.okujajoshua.reha.R

/**
 * A simple [Fragment] subclass.
 */
class SplashScreenFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //get shared preference from here and check if user is logged in
        //if true go to balance page
        //else go to login page

        Handler().postDelayed({
            findNavController().navigate(R.id.signinFragment,null)
        },3000)

    }

}
