package com.example.dexdogs.activities.auth

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dexdogs.R
import com.example.dexdogs.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {

    interface LoginFragmentListener {
        fun onRegisterClick()
    }

    private lateinit var loginFragmentListener: LoginFragmentListener

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
        val binding = FragmentLoginBinding.inflate(inflater)
        binding.loginRegisterButton.setOnClickListener {
            loginFragmentListener.onRegisterClick()
        }
        return binding.root
    }

}