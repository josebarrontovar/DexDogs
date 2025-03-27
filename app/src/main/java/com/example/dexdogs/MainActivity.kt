package com.example.dexdogs

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.dexdogs.data.remote.AuthenticationInterceptor
import com.example.dexdogs.presentation.ui.auth.LoginActivity
import com.example.dexdogs.presentation.ui.settings.SettingsActivity
import com.example.dexdogs.databinding.ActivityMainBinding
import com.example.dexdogs.domain.model.User
import android.Manifest
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import com.example.dexdogs.presentation.ui.dogList.DogListActivity

class MainActivity : AppCompatActivity() {

    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                // Permission is granted. Continue the action or workflow in your
                // app.
            } else {
               Toast.makeText(this, "No tienes permisos", Toast.LENGTH_SHORT).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val user = User.getLoggedUser(this)
        if (user == null) {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            AuthenticationInterceptor.setSessionToken(user.authenticationToken)
        }

        binding.settings.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }

        binding.dogList.setOnClickListener {
            val intent = Intent(this, DogListActivity::class.java)
            startActivity(intent)
        }
        requestCameraPermission()
    }

    private fun requestCameraPermission() {
        when {
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED -> {
                // You can use the API that requires the permission.
            }

            ActivityCompat.shouldShowRequestPermissionRationale(
                this, Manifest.permission.CAMERA
            ) -> {
                AlertDialog.Builder(this)
                    .setTitle("Permission required")
                    .setMessage("This permission is needed to show camera preview")
                    .setPositiveButton("Ok") { _, _ ->
                        requestPermissionLauncher.launch(
                            Manifest.permission.CAMERA)
                            }.setNegativeButton("Cancel") { dialog, _ -> dialog.dismiss() }
                    .show()
            }

            else -> {
                // You can directly ask for the permission.
                // The registered ActivityResultCallback gets the result of this request.
                requestPermissionLauncher.launch(
                    Manifest.permission.CAMERA
                )
            }
        }
    }

}