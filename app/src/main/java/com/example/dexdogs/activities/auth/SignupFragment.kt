package com.example.dexdogs.activities.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.dexdogs.databinding.FragmentSignupBinding

class SignupFragment : Fragment() {

    private lateinit var binding: FragmentSignupBinding

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
            binding.passwordInput.error = "Password must be at least 5 characters"
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

    }

    private fun validatePassword(password: String): Boolean {
        return password.length > 5
    }

    private fun validateEmail(email:String?):Boolean{
        return !email.isNullOrEmpty() &&
                android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()

    }

}