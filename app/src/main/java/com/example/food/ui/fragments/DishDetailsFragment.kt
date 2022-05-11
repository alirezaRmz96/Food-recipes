package com.example.food.ui.fragments

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.food.R
import com.example.food.data.util.Resource
import com.example.food.databinding.FragmentDishDetailsBinding
import com.example.food.ui.MainActivity
import com.example.food.ui.viewModel.FoodViewModel

class DishDetailsFragment : Fragment() {

    private lateinit var fragmentDishDetailsBinding: FragmentDishDetailsBinding
    private lateinit var foodViewModel : FoodViewModel
    private var mProgressDialog : Dialog? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dish_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentDishDetailsBinding = FragmentDishDetailsBinding.bind(view)
        foodViewModel = (activity as MainActivity).foodViewModel
        val args: DishDetailsFragmentArgs by navArgs()
//        val foodRecep = args.selectedFood
        val category = args.selectedCategory
//        foodViewModel.getRecepFromId(foodRecep.id)

        foodViewModel.foodRecepFromID.observe(viewLifecycleOwner){ response->

            when(response){
                is Resource.Success -> {
                    hideProgressDialog()
                    fragmentDishDetailsBinding.scrollForDetails.visibility = View.VISIBLE
                    Log.d("TAG1", "Success getRecepFromId: Success")
                    response.data?.let {
                        fragmentDishDetailsBinding.apply {
                            Glide.with(ivDishImage.context)
                                .load(it.image)
                                .into(ivDishImage)
                            tvDishCategory.text = category
                            tvDishName.text = it.title
                            tvDishTime.text =getString(R.string.dish_time_ready,it.readyInMinutes.toString())
                            tvDishServings.text =getString(R.string.dish_servings,it.servings.toString() )
                            tvDishPrice.text =getString(R.string.dish_dollar, it.pricePerServing.toString())
                            tvDishInstructions.text = it.instructions
                            tvDishSummary.text = it.summary
                        }
                    }
                }
                is Resource.Error -> {
                    showCustomDialog()
                    Log.d("TAG1", "Error getRecepFromId: Error")
                    Toast.makeText(context, "Some things went to wrong", Toast.LENGTH_SHORT).show()
                }
                is Resource.Loading ->{
                    showCustomDialog()
                    Log.d("TAG1", "Loading getRecepFromId: Loading")
                }
            }
        }
    }
    private fun showCustomDialog(){
        mProgressDialog = Dialog(requireActivity())

        mProgressDialog?.let {
            it.setContentView(R.layout.custom_progress)
            it.show()
        }
    }
    private fun hideProgressDialog() {
        mProgressDialog?.let {
            it.dismiss()
        }
    }
}