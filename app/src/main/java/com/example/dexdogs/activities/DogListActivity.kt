package com.example.dexdogs.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dexdogs.R
import com.example.dexdogs.activities.adapter.DogAdapter
import com.example.dexdogs.activities.dogDetail.DogDetailActivity
import com.example.dexdogs.activities.dogDetail.DogDetailActivity.Companion.DOG_EXTRA
import com.example.dexdogs.api.ApiResponseStatus
import com.example.dexdogs.databinding.ActivityDogListBinding
import com.example.dexdogs.model.Dog
import com.example.dexdogs.repository.DogRepository
import com.example.dexdogs.viewmodel.DogListViewModel

class DogListActivity : AppCompatActivity() {

    private val dogViewModel: DogListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityDogListBinding = ActivityDogListBinding.inflate(layoutInflater)
        setContentView(activityDogListBinding.root)

        val loadingWheel = activityDogListBinding.loadingWheel
        val recycler = activityDogListBinding.dogRecycler
        recycler.layoutManager = LinearLayoutManager(this)

        val adapter = DogAdapter()

        adapter.setOnItemClick({
            val intent = Intent(this, DogDetailActivity::class.java)
            intent.putExtra(DOG_EXTRA, it)
            startActivity(intent)
        })

        recycler.adapter = adapter

        dogViewModel.dogList.observe(this) {
            adapter.submitList(it)
        }

        dogViewModel.apiResponseStatus.observe(this) {
            when (it) {
                is ApiResponseStatus.Error -> {
                    loadingWheel.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }
                is ApiResponseStatus.Loading -> loadingWheel.visibility = View.VISIBLE
                is ApiResponseStatus.Success -> loadingWheel.visibility = View.GONE
            }
        }


    }

}
