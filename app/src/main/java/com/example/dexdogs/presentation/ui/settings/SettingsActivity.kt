package com.example.dexdogs.presentation.ui.settings

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.dexdogs.presentation.ui.auth.LoginActivity
import com.example.dexdogs.databinding.ActivitySettingsBinding
import com.example.dexdogs.domain.model.User

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ButtonSettings.setOnClickListener {
            logout()
        }
    }

    private fun logout() {
        User.removeSharedPreference(this)
        val intent = Intent(this, LoginActivity::class.java)
        intent.flags=Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }
}