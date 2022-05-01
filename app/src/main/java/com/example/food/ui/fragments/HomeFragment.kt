package com.example.food.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.HorizontalScrollView
import android.widget.LinearLayout.HORIZONTAL
import android.widget.LinearLayout.VERTICAL
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.food.R
import com.example.food.data.Category
import com.example.food.databinding.FragmentHomeBinding
import com.example.food.ui.adapter.CategoryItemAdapter

class HomeFragment : Fragment() {

    private lateinit var categoryItemAdapter: CategoryItemAdapter
    private lateinit var binding: FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)
        categoryItemAdapter = CategoryItemAdapter()
        initRecyclerView()

    }

    private fun initRecyclerView() {
        val categoryList = listOf(
            Category(R.drawable.food,"All"),
            Category(R.drawable.caramel,"Caramel") ,
            Category(R.drawable.fish,"Fish") ,
            Category(R.drawable.meat,"Meat") ,
            Category(R.drawable.papaya,"Papaya") ,
            Category(R.drawable.salt,"Salt") ,
            Category(R.drawable.salmon,"Salmon") ,
            Category(R.drawable.sushi,"Sushi") ,
            Category(R.drawable.watermelon,"Watermelon") ,
        )
        binding.rlCate.apply {
            adapter = categoryItemAdapter
            layoutManager = LinearLayoutManager(activity, HORIZONTAL,false)

        }
        categoryItemAdapter.differ.submitList(categoryList)
    }

}


