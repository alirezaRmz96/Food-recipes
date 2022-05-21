package com.example.food.ui.fragments

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.food.R
import com.example.food.data.model.receFromId.RecepFromIdList
import com.example.food.databinding.FragmentDishDetailsBinding
import com.example.food.ui.MainActivity
import com.example.food.ui.viewModel.FoodDBViewModel
import com.example.food.ui.viewModel.FoodViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog

class DishDetailsFragment : Fragment() {

    private var fragmentDishDetailsBinding: FragmentDishDetailsBinding? = null
    private lateinit var foodViewModel: FoodViewModel
    private var mProgressDialog: Dialog? = null
    private var recepFromIdList: RecepFromIdList? = null

    private val foodDBViewModel: FoodDBViewModel by lazy {
        (activity as MainActivity?)!!.getSharedViewModel(FoodDBViewModel::class.java)
    }

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
        // if i don't observe it i cant see my favorite dish
        foodViewModel.allDishesList.observe(viewLifecycleOwner) {
            Log.d("TAG", "show fav favorite: " + it.size)
        }

        foodViewModel.loadRandomDish.observe(viewLifecycleOwner){ load->
            if (load){
                showCustomDialog()
                fragmentDishDetailsBinding!!.scrollForDetails.visibility = View.GONE
            }
            else{
                hideProgressDialog()
                fragmentDishDetailsBinding!!.scrollForDetails.visibility = View.VISIBLE
            }
        }
        foodViewModel.foodRecepFromID.observe(viewLifecycleOwner) { food ->
            Log.d("TAG", "show fav: " + food)
            showSpecialDish(food,category)
        }
    }

    private fun showSpecialDish(food:RecepFromIdList,category:String) {
        fragmentDishDetailsBinding?.apply {
            ivBack.setOnClickListener {
                requireActivity().onBackPressed()
            }
            ivShare.setOnClickListener {
                bottomSheet()
            }
            Glide.with(ivDishImage.context)
                .load(food.image)
                .into(ivDishImage)
            tvDishCategory.text = category
            tvDishName.text = food.title
            tvDishTime.text =
                getString(R.string.dish_time_ready, food.readyInMinutes.toString())
            tvDishServings.text =
                getString(R.string.dish_servings, food.servings.toString())
            tvDishPrice.text =
                getString(R.string.dish_dollar, food.pricePerServing.toString())
            tvDishInstructions.text = food.instructions
            tvDishSummary.text = food.summary
            if (food.favoriteDish) {
                ivFavorDish.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireActivity(),
                        R.drawable.ic_favorite_selected
                    )
                )
            } else {
                ivFavorDish.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireActivity(),
                        R.drawable.ic_favorite_border
                    )
                )
            }
            Log.d("TAG", "show  recep: " + food.favoriteDish)
            ivFavorDish.setOnClickListener {
                Log.d("TAG", "show  recep: " + food.favoriteDish)
                if (food.favoriteDish) {
                    foodDBViewModel.delete(food)
                    ivFavorDish.setImageDrawable(
                        ContextCompat.getDrawable(
                            requireActivity(),
                            R.drawable.ic_favorite_border
                        )
                    )
                    Toast.makeText(
                        requireActivity(),
                        resources.getString(R.string.msg_already_added_to_favorites),
                        Toast.LENGTH_SHORT
                    ).show()
                    food.favoriteDish = false
                } else {
                    recepFromIdList = RecepFromIdList(
                        food.id,
                        food.image,
                        food.imageType,
                        food.instructions,
                        food.pricePerServing,
                        food.readyInMinutes,
                        food.servings,
                        food.sourceName,
                        food.sourceUrl,
                        food.spoonacularSourceUrl,
                        food.summary,
                        food.title,
                        true
                    )
                    Log.d("TAG", "show recep in fragment " + recepFromIdList)
                    foodViewModel.insert(recepFromIdList!!)
                    ivFavorDish.setImageDrawable(
                        ContextCompat.getDrawable(
                            requireActivity(),
                            R.drawable.ic_favorite_selected
                        )
                    )
                    Toast.makeText(
                        requireActivity(),
                        resources.getString(R.string.msg_added_to_favorites),
                        Toast.LENGTH_SHORT
                    ).show()
                    food.favoriteDish = true
                }
            }
        }
    }

    private fun bottomSheet() {
        val dialog = BottomSheetDialog(requireActivity())
        val view = layoutInflater.inflate(R.layout.bottom_sheet_dialog, null)
        val btnClose = view.findViewById<Button>(R.id.idBtnDismiss)
        btnClose.setOnClickListener {
            dialog.dismiss()
        }
        //dialog.setCancelable(false)
        dialog.setContentView(view)
        dialog.show()
    }

    private fun showCustomDialog() {
        mProgressDialog = Dialog(requireActivity())
        mProgressDialog?.let {
            it.setContentView(R.layout.custom_progress)
            it.show()
        }
    }

    private fun hideProgressDialog() {
        mProgressDialog?.dismiss()
    }


    override fun onDestroy() {
        super.onDestroy()
        fragmentDishDetailsBinding = null
        recepFromIdList = null
    }
}