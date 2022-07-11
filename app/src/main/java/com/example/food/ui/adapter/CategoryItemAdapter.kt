package com.example.food.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.food.data.util.Category
import com.example.food.databinding.ItemCategoryBinding

/***
 *  for get and set list we use DiffUtil so we can check that's list is same or not then pass it
 * This class finds the difference between two lists and provides the updated list as an output.
 *  This class is used to notify updates to a RecyclerView Adapter.
 * **/
/***
 *  still has problem ->> cant design cate btn the way i want :(
 * **/

class CategoryItemAdapter : RecyclerView.Adapter<CategoryItemAdapter.CategoryViewHolder>() {

    private val callback = object : DiffUtil.ItemCallback<Category>() {
        override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem.CategoryName == newItem.CategoryName
        }

        override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this, callback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding =
            ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(category: Category) {

            binding.cateText.text = category.CategoryName
            binding.cateImage.setImageResource(category.CategoryImage)

            binding.root.setOnClickListener {
                onItemClickListener?.let {
                    it(category)
                }
            }
        }
    }

    private var onItemClickListener: ((Category) -> Unit)? = null
    fun setOnItemClickListener(listener: (Category) -> Unit) {
        onItemClickListener = listener
    }

}