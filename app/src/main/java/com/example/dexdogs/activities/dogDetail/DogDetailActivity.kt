package com.example.dexdogs.activities.dogDetail

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.dexdogs.R
import com.example.dexdogs.databinding.ActivityDogDetailBinding
import com.example.dexdogs.model.Dog


class DogDetailActivity : AppCompatActivity() {

    companion object {
        const val DOG_EXTRA = "dog_Extra"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding= ActivityDogDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val dog=intent.getParcelableExtra<Dog>(DOG_EXTRA)
        if(dog == null){
            finish()
            return
        }
        binding.dogIndex.text=getString(R.string.dog_index_format,dog.index)
        binding.lifeExpectancy.text=getString(R.string.dog_life_expectancy_format,dog.lifeExpectancy)
        binding.dog=dog


    }
}