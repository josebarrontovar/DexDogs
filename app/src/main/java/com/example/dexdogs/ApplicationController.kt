package com.example.dexdogs

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.dexdogs.data.remote.ApiServiceInterceptor
import com.example.dexdogs.presentation.ui.auth.LoginActivity
import com.example.dexdogs.presentation.ui.settings.SettingsActivity
import com.example.dexdogs.databinding.ActivityMainBinding
import com.example.dexdogs.domain.model.User
import android.Manifest
import android.app.Application
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import com.example.dexdogs.presentation.ui.dogList.DogListActivity

class ApplicationController : Application() {
    companion object {
        lateinit var instance: ApplicationController
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}