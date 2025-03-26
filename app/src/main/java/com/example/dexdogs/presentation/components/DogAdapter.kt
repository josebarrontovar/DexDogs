package com.example.dexdogs.presentation.components

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.dexdogs.R
import com.example.dexdogs.databinding.DogListItemBinding
import com.example.dexdogs.domain.model.Dog

class DogAdapter() : ListAdapter<Dog, DogAdapter.DogViewHolder>(DogDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHolder {
        val dogViewHolder = DogListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return DogViewHolder(dogViewHolder)
    }

    inner class DogViewHolder(val binding: DogListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

            fun bind(dog: Dog){
                if(dog.inCollection) {
                    binding.dogListItemLayout.background=AppCompatResources.getDrawable(binding.dogImage.context, R.drawable.dog_list_item)
                    binding.dogImage.visibility=android.view.View.VISIBLE
                    binding.dogId.visibility=android.view.View.GONE

                    binding.dogListItemLayout.setOnClickListener {
                        onItemClickListener?.invoke(dog)
                    }
                    binding.dogListItemLayout.setOnLongClickListener {
                        onLongClickListener?.invoke(dog)
                        true
                    }
                    binding.dogImage.load(dog.imageUrl)
                } else{
                    binding.dogImage.setImageDrawable(null)
                    binding.dogImage.visibility=android.view.View.GONE
                    binding.dogId.visibility=android.view.View.VISIBLE
                    binding.dogId.text = dog.index.toString()
                    binding.dogId.setTextColor(ContextCompat.getColor(binding.dogId.context, R.color.white))
                    binding.dogListItemLayout.background=ContextCompat.getDrawable(binding.dogImage.context, R.drawable.dog_list_item_null)

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

    private var onLongClickListener:((Dog) -> Unit)? =null
    fun setOnLongClick(listener: (Dog)->Unit){
        this.onLongClickListener = listener
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
