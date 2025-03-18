package com.example.dexdogs.activities.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.dexdogs.databinding.DogListItemBinding
import com.example.dexdogs.model.Dog

class DogAdapter() : ListAdapter<Dog, DogAdapter.DogViewHolder>(DogDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHolder {
        val dogViewHolder = DogListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return DogViewHolder(dogViewHolder)
    }

    inner class DogViewHolder(val binding: DogListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

            fun bind(dog: Dog){
                binding.dogName.text = dog.name
                binding.dogName.setOnClickListener {
                    onItemClickListener?.invoke(dog)
                }
            }
    }

    override fun onBindViewHolder(holder: DogViewHolder, position: Int) {
        val dog = getItem(position)
        holder.bind(dog)
    }

    private var onItemClickListener:((Dog) -> Unit)? =null
    fun setOnItemClick(listener: (Dog)->Unit){
        this.onItemClickListener = listener
    }

    // DiffUtil.ItemCallback para comparar elementos Dog
    companion object DogDiffCallback : DiffUtil.ItemCallback<Dog>() {
        override fun areItemsTheSame(oldItem: Dog, newItem: Dog): Boolean {
            // Compara si los elementos son los mismos (por ejemplo, comparando un ID único)
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Dog, newItem: Dog): Boolean {
            // Compara si los contenidos de los elementos son los mismos
            return oldItem == newItem
        }
    }



}
