package com.example.food.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout.HORIZONTAL
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
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
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private lateinit var categoryItemAdapter: CategoryItemAdapter
    private lateinit var informationAdapter: InformationAdapter
    private lateinit var mShimmerViewContainer: ShimmerFrameLayout
    private lateinit var binding: FragmentHomeBinding
    private var category = String()

    /*I can use another definition for view model
    private val foodViewModels : FoodViewModel by lazy {
        ViewModelProvider(requireActivity()).get(FoodViewModel::class.java)
    }*/
    private val foodViewModel: FoodViewModel by lazy {
        (activity as MainActivity?)!!.getSharedViewModel(FoodViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        /** i can use another definition for inflate
        binding = FragmentHomeBinding.inflate(layoutInflater,container,false)
        return binding.root
         */
        return inflater.inflate(R.layout.fragment_home, container, false)

    }

    override fun onResume() {
        super.onResume()
        if (requireActivity() is MainActivity) {
            (activity as MainActivity?)!!.showBottomNavigationView()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        mShimmerViewContainer = binding.shimmer
        categoryItemAdapter = CategoryItemAdapter()
        informationAdapter = InformationAdapter()


        categoryItemAdapter.setOnItemClickListener { name ->
            Log.d("TAG", "get1: " + name.CategoryName)

            foodViewModel.getInformationFood(name.CategoryName)
        }

        informationAdapter.setOnItemClickListenerInformation {
            foodViewModel.getRecepFromId(it.id)

            val bundle = Bundle().apply {
                putString("selected_category", category)
            }
            if (requireActivity() is MainActivity) {
                (activity as MainActivity?)!!.hideBottomNavigationView()
            }

            findNavController().navigate(
                R.id.action_homeFragment_to_dishDetailsFragment2,
                bundle
            )

        }

        initRecyclerView()

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    foodViewModel.foodInformationFlow.collect { response ->
                        when (response) {
                            is Resource.Success -> {
                                mShimmerViewContainer.stopShimmer()
                                mShimmerViewContainer.visibility = View.GONE
                                response.data?.let { specialFood ->
                                    Log.d("TAG", "Data InformationFood: ")
                                    informationAdapter.differAllFood.submitList(specialFood)
                                }
                            }
                            is Resource.Error -> {
                                mShimmerViewContainer.startShimmer()
                                Toast.makeText(context, response.message, Toast.LENGTH_SHORT)
                                    .show()
                            }
                            is Resource.Loading -> {
                                Log.d("TAG", "Loading InformationFood: Loading")
                                mShimmerViewContainer.startShimmer()
                                mShimmerViewContainer.visibility = View.VISIBLE
                            }
                        }
                    }
                }
            }
        }
    }

    private fun initRecyclerView() {
        val categoryList = listOf(
            Category(R.drawable.caramel, "Caramel"),
            Category(R.drawable.fish, "Fish"),
            Category(R.drawable.meat, "Meat"),
            Category(R.drawable.papaya, "Papaya"),
            Category(R.drawable.salt, "Salt"),
            Category(R.drawable.salmon, "Salmon"),
            Category(R.drawable.sushi, "Sushi"),
            Category(R.drawable.watermelon, "Watermelon"),
        )
        binding.rlCate.apply {
            adapter = categoryItemAdapter
            layoutManager = LinearLayoutManager(activity, HORIZONTAL, false)

        }
        categoryItemAdapter.differ.submitList(categoryList)

        binding.rlInformation.apply {
            adapter = informationAdapter
            layoutManager = LinearLayoutManager(activity, HORIZONTAL, false)
        }

    }


}


