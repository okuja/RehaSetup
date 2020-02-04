package com.okujajoshua.reha.presentation.signin


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.okujajoshua.reha.R
import com.okujajoshua.reha.database.RehaDatabase
import com.okujajoshua.reha.databinding.FragmentSigninBinding

/**
 * A simple [Fragment] subclass.
 */
class SigninFragment : Fragment() {

    private lateinit var email:String
    private lateinit var password: String
    private lateinit var signinViewModel: SigninViewModel
    private lateinit var signinViewModelFactory: SigninViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentSigninBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_signin,container, false)

        val application = requireNotNull(this.activity).application

        val dataSource = RehaDatabase.getInstance(application).rehaUserDao

        signinViewModelFactory = SigninViewModelFactory(dataSource)

        signinViewModel = ViewModelProviders.of(this,signinViewModelFactory).get(SigninViewModel::class.java)

        binding.setLifecycleOwner (this)

        binding.signInViewModel = signinViewModel

        signinViewModel.navigateToBalance.observe(this, Observer{
            if(it == true){
                this.findNavController().navigate(
                    SigninFragmentDirections.actionSigninFragmentToBalanceFragment(signinViewModel.currentuser.value!!.email,signinViewModel.currentuser.value!!.password))
                signinViewModel.doneNavigating()
            }
        })

//        binding.signinButton.setOnClickListener{ view : View ->
//            email = binding.emailAddress.text.toString()
//            password = binding.passwordEdit.text.toString()
//            view.findNavController().navigate(SigninFragmentDirections.actionSigninFragmentToBalanceFragment(email,password))
//        }

        binding.registerButton.setOnClickListener{ view: View ->
            view.findNavController().navigate(SigninFragmentDirections.actionSigninFragmentToSignupFragment())
        }


        return binding.root
    }


}
