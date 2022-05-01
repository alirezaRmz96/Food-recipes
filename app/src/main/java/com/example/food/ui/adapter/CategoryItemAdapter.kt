package com.example.food.ui.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.food.R
import com.example.food.data.Category
import com.example.food.databinding.ItemCategoryBinding
import kotlin.properties.Delegates

/**
 *  for get and set list we use DiffUtil so we can check that's list is same or not then pass it
 * This class finds the difference between two lists and provides the updated list as an output.
 *  This class is used to notify updates to a RecyclerView Adapter.
 * **/

class CategoryItemAdapter : RecyclerView.Adapter<CategoryItemAdapter.CategoryViewHolder>(){

    private var isChecked by Delegates.notNull<Boolean>()
    private val callback = object : DiffUtil.ItemCallback<Category>(){
        override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem.CategoryName == newItem.CategoryName
        }

        override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this,callback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = ItemCategoryBinding
            .inflate(LayoutInflater.from(parent.context),parent,false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = differ.currentList[position]
        holder.bind(category)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    inner class CategoryViewHolder(
        private val binding: ItemCategoryBinding
    ):RecyclerView.ViewHolder(binding.root){
        @SuppressLint("ResourceAsColor")
        fun bind(category: Category){
            isChecked = false
            binding.cateText.text = category.CategoryName
            //val bitmap = BitmapFactory.decodeResource(Resources(),category.CategoryImage)
            binding.cateImage.setImageResource(category.CategoryImage)
           // binding.cateImage.setBackgroundResource(R.drawable.rounded_circle)
            binding.root.setOnClickListener {
                isChecked =! isChecked
                if (isChecked)  binding.cateImage.setBackgroundResource(R.drawable.rounded_circle_un)
                else binding.cateImage.setBackgroundResource(R.drawable.rounded_circle)
                onItemClickListener?.let {
                    it(category)
                }
            }
        }
        private var onItemClickListener : ((Category) -> Unit)? = null

        fun setOnItemClickListener(listener:(Category) -> Unit){
            onItemClickListener = listener
        }
    }


}