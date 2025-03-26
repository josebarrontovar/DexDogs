package com.example.dexdogs.presentation.ui.auth

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.dexdogs.databinding.FragmentSignupBinding

class SignupFragment : Fragment() {

    private lateinit var binding: FragmentSignupBinding

    interface SignupFragmentListener {
        fun onSingupFielsValidated(email:String,password:String, confirmPassword:String)
    }

    private lateinit var signupFragmentListener: SignupFragmentListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        signupFragmentListener = try {
            context as SignupFragmentListener
        } catch (e: ClassCastException) {
            throw ClassCastException("$context must implement LoginFragmentListener")
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignupBinding.inflate(inflater)
        setUpSignUpButton(binding)
        return binding.root
    }

    private fun setUpSignUpButton(binding: FragmentSignupBinding) {
        binding.signUpButton.setOnClickListener{
            validateFiels()
        }
    }

    private fun validateFiels() {
        binding.emailInput.error=""
        binding.passwordInput.error=""
        binding.confirmPasswordInput.error=""
       val email= binding.emailEdit.text.toString()
        if(!validateEmail(email))
        {
            binding.emailInput.error = "Email is not valid"
            return
        }
        val password = binding.passwordEdit.text.toString()
        if(!validatePassword(password)){
            binding.passwordInput.error = "Password must be at least 2 characters"
            return
        }
        val confirmPassword = binding.confirmPasswordEdit.text.toString()
        if(!validatePassword(confirmPassword)){
            binding.confirmPasswordInput.error = "Password must be at least 8 characters"
            return
        }
        if(password != confirmPassword){
            binding.confirmPasswordInput.error = "Passwords do not match"
            return
        }
        signupFragmentListener.onSingupFielsValidated(email,password,confirmPassword)

    }

    private fun validatePassword(password: String): Boolean {
        return password.length > 2
    }

    private fun validateEmail(email:String?):Boolean{
        return !email.isNullOrEmpty() &&
                android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()

    }

}