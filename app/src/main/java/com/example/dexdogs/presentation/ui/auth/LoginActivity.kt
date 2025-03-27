package com.example.dexdogs.presentation.ui.auth

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.example.dexdogs.R
import com.example.dexdogs.MainActivity
import com.example.dexdogs.data.remote.ApiResponseStatus
import com.example.dexdogs.databinding.ActivityLoginBinding
import com.example.dexdogs.domain.model.User
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity(), LoginFragment.LoginFragmentListener,
    SignupFragment.SignupFragmentListener {

    private val viewModel by viewModels<AuthViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding= ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val loadingWheel = binding.loadingWheel
        loadingWheel.visibility = View.GONE
        viewModel.apiResponseStatus.observe(this) {
            when (it) {
                is ApiResponseStatus.Error -> {
                    loadingWheel.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }
                is ApiResponseStatus.Loading -> loadingWheel.visibility = View.VISIBLE
                is ApiResponseStatus.Success -> loadingWheel.visibility = View.GONE
            }
        }

        viewModel.user.observe(this){
            if(it != null){
                User.setLoggedUser(this,it)
                StartMainActivity()
            }
        }
    }

    private fun StartMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onRegisterClick(){
        findNavController(R.id.nav_host_fragment).navigate(LoginFragmentDirections.actionLoginFragmentToSignupFragment())
    }

    override fun onLoginFieldsValidated(email: String, password: String) {
       viewModel.login(email,password)
    }

    override fun onSingupFielsValidated(email: String, password: String, confirmPassword: String) {
       viewModel.singUp(email,password,confirmPassword)
    }
}