package com.example.dexdogs.presentation.ui.dogList

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dexdogs.presentation.components.DogAdapter
import com.example.dexdogs.presentation.ui.dogDetail.DogDetailActivity
import com.example.dexdogs.presentation.ui.dogDetail.DogDetailActivity.Companion.DOG_EXTRA
import com.example.dexdogs.data.remote.ApiResponseStatus
import com.example.dexdogs.databinding.ActivityDogListBinding


class DogListActivity : AppCompatActivity() {

    private val dogViewModel: DogListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityDogListBinding = ActivityDogListBinding.inflate(layoutInflater)
        setContentView(activityDogListBinding.root)

        val loadingWheel = activityDogListBinding.loadingWheel
        val recycler = activityDogListBinding.dogRecycler
        recycler.layoutManager = GridLayoutManager(this,3)

        val adapter = DogAdapter()

        adapter.setOnItemClick({
            val intent = Intent(this, DogDetailActivity::class.java)
            intent.putExtra(DOG_EXTRA, it)
            startActivity(intent)
        })

        adapter.setOnLongClick({
            dogViewModel.addDogToUser(it.id)
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
