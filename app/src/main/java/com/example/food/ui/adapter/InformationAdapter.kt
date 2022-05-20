package com.example.food.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.food.data.model.allList.AllFoodResultList
import com.example.food.databinding.ItemInformationBinding

class InformationAdapter:RecyclerView.Adapter<InformationAdapter.InformationViewHolder> (){

    private val callbackAllFood = object : DiffUtil.ItemCallback<AllFoodResultList>(){
        override fun areItemsTheSame(oldItem: AllFoodResultList, newItem: AllFoodResultList): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: AllFoodResultList, newItem: AllFoodResultList): Boolean {
            return oldItem == newItem
        }

    }

    val differAllFood = AsyncListDiffer(this,callbackAllFood)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InformationViewHolder {
        val binding = ItemInformationBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return InformationViewHolder(binding)
    }

    override fun onBindViewHolder(holder: InformationViewHolder, position: Int) {
        val information = differAllFood.currentList[position]
        holder.bind(information)
    }

    override fun getItemCount(): Int {
        return differAllFood.currentList.size
    }

    inner class InformationViewHolder(
        private val binding: ItemInformationBinding
    ):RecyclerView.ViewHolder(binding.root){
        fun bind(food: AllFoodResultList){

            binding.tvFood.text = food.title

            Glide.with(binding.ivFood.context)
                .load(food.image)
                .into(binding.ivFood)

            binding.root.setOnClickListener {
                onClickItemListener?.let {
                    it(food)
                }
            }
        }
    }
    private var onClickItemListener : ((AllFoodResultList) -> Unit)? = null
    fun setOnItemClickListenerInformation(listener : (AllFoodResultList) -> Unit){
        onClickItemListener = listener
    }

}