package com.okujajoshua.reha.presentation.signin


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.okujajoshua.reha.R
import com.okujajoshua.reha.databinding.FragmentSigninBinding
import timber.log.Timber

/**
 * A simple [Fragment] subclass.
 */
class SigninFragment : Fragment() {

    private lateinit var email:String
    private lateinit var password: String
    private lateinit var viewModel: SigninViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentSigninBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_signin,container, false)

        Timber.i("Called viewModelProviders.of")
        viewModel = ViewModelProviders.of(this).get(SigninViewModel::class.java)


        binding.signinButton.setOnClickListener{ view : View ->
            email = binding.emailAddress.text.toString()
            password = binding.passwordEdit.text.toString()
            view.findNavController().navigate(SigninFragmentDirections.actionSigninFragmentToBalanceFragment(email,password))
        }

        binding.registerButton.setOnClickListener{ view: View ->
            view.findNavController().navigate(SigninFragmentDirections.actionSigninFragmentToSignupFragment())
        }
        return binding.root
    }


}
