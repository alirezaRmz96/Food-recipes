package com.example.food.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout.HORIZONTAL
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.food.R
import com.example.food.data.util.Category
import com.example.food.data.util.Resource
import com.example.food.databinding.FragmentHomeBinding
import com.example.food.ui.MainActivity
import com.example.food.ui.adapter.CategoryItemAdapter
import com.example.food.ui.adapter.InformationAdapter
import com.example.food.ui.viewModel.FoodViewModel
import com.facebook.shimmer.ShimmerFrameLayout

class HomeFragment : Fragment() {

    private lateinit var categoryItemAdapter: CategoryItemAdapter
    private lateinit var informationAdapter: InformationAdapter
    private lateinit var mShimmerViewContainer :  ShimmerFrameLayout ;
    private lateinit var binding: FragmentHomeBinding
    private lateinit var foodViewModel: FoodViewModel

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
        foodViewModel = (activity as MainActivity).foodViewModel
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)
        mShimmerViewContainer = binding.shimmer
        categoryItemAdapter = CategoryItemAdapter()
        informationAdapter = InformationAdapter()

        initRecyclerView()
        setUpView()

    }
    private fun setUpView(){
        foodViewModel.getAllFood()
        foodViewModel.foodLiveData.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success -> {
                    //binding.shimmerViewContent.hideShimmer()
                    response.data?.let {
                        Log.d("TAG", "setUpView: ${it.results}")

                        informationAdapter.differ.submitList(it.results)
                        mShimmerViewContainer.stopShimmer()
                        mShimmerViewContainer.visibility = View.GONE
                    }
                }
                is Resource.Error -> {
                    Log.d("TAG", "setUpView: Error")
                    mShimmerViewContainer.startShimmer()
                    Toast.makeText(context,"Some things went to wrong",Toast.LENGTH_SHORT).show()
                }
                is Resource.Loading -> {
                    Log.d("TAG", "setUpView: Loading")
                    mShimmerViewContainer.startShimmer()
                    mShimmerViewContainer.visibility = View.VISIBLE
                }
            }
        }
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
        binding.rlInformation.apply {
            adapter = informationAdapter
            layoutManager = LinearLayoutManager(activity, HORIZONTAL,false)
        }

    }

}


