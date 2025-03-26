package com.example.dexdogs.activities.auth

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.dexdogs.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {

    interface LoginFragmentListener {
        fun onRegisterClick()
        fun onLoginFieldsValidated(email: String, password: String)
    }

    private lateinit var loginFragmentListener: LoginFragmentListener
    private lateinit var binding: FragmentLoginBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        loginFragmentListener = try {
            context as LoginFragmentListener
        } catch (e: ClassCastException) {
            throw ClassCastException("$context must implement LoginFragmentListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater)
        binding.loginRegisterButton.setOnClickListener {
            loginFragmentListener.onRegisterClick()
        }
        binding.loginButton.setOnClickListener {
            validateFiels()
        }
        return binding.root
    }

    private fun validateFiels() {
        binding.emailInput.error = ""
        binding.passwordInput.error = ""
        val email = binding.emailEdit.text.toString()
        if (!validateEmail(email)) {
            binding.emailInput.error = "Email is not valid"
            return
        }
        val password = binding.passwordEdit.text.toString()
        if (!validatePassword(password)) {
            binding.passwordInput.error = "Password must be at least 2 characters"
            return
        }
        loginFragmentListener.onLoginFieldsValidated(email, password)

    }

    private fun validatePassword(password: String): Boolean {
        return password.length > 2
    }

    private fun validateEmail(email: String?): Boolean {
        return !email.isNullOrEmpty() &&
                android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()

    }

}