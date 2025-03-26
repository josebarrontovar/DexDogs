package com.example.dexdogs.presentation.ui.dogDetail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.example.dexdogs.R
import com.example.dexdogs.databinding.ActivityDogDetailBinding
import com.example.dexdogs.domain.model.Dog


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
        binding.dogImage.load(dog.imageUrl)
        binding.dog=dog

        binding.closeButton.setOnClickListener{
            finish()
        }


    }
}