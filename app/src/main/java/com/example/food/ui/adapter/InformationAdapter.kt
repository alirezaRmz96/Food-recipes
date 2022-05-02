package com.example.food.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.food.data.model.allList.Result
import com.example.food.databinding.ItemInformationBinding

class InformationAdapter:RecyclerView.Adapter<InformationAdapter.InformationViewHolder> (){

    private val callback = object : DiffUtil.ItemCallback<Result>(){
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this,callback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InformationViewHolder {
        val binding = ItemInformationBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return InformationViewHolder(binding)
    }

    override fun onBindViewHolder(holder: InformationViewHolder, position: Int) {
        val information = differ.currentList[position]
        holder.bind(information)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    inner class InformationViewHolder(
        private val binding: ItemInformationBinding
    ):RecyclerView.ViewHolder(binding.root){
        fun bind(food: Result){

            binding.tvFood.text = food.title

            Glide.with(binding.ivFood.context)
                .load(food.image)
                .into(binding.ivFood)
        }
    }


}