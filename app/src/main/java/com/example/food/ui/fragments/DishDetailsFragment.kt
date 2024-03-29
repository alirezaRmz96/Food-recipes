package com.example.food.ui.fragments

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.food.R
import com.example.food.data.model.receFromId.RecepFromIdList
import com.example.food.data.util.Resource
import com.example.food.databinding.FragmentDishDetailsBinding
import com.example.food.ui.MainActivity
import com.example.food.ui.viewModel.FoodViewModel
import com.example.food.ui.viewModel.FoodEvent
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.coroutines.launch

class DishDetailsFragment : Fragment() {

    private var binding: FragmentDishDetailsBinding? = null
    private var mProgressDialog: Dialog? = null
    private var recepFromIdList: RecepFromIdList? = null
    private var recepList: RecepFromIdList? = null
    private var category : String? = ""

    private val foodViewModel : FoodViewModel by lazy {
        (activity as MainActivity?)!!.getSharedViewModel(FoodViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dish_details, container, false)
    }

    @SuppressLint("UnsafeRepeatOnLifecycleDetector")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDishDetailsBinding.bind(view)

        val args: DishDetailsFragmentArgs by navArgs()
        category = args.selectedCategory


        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    showCustomDialog()
                    foodViewModel.foodRecepFromIDFlow.collect { response ->
                        recepList = response.data
                        Log.d("TAG", "details 1: " + response.data)
                        when (response) {
                            is Resource.Success -> {
                                binding!!.scrollForDetails.visibility = View.VISIBLE
                                hideProgressDialog()
                                response.data?.let { food ->
                                    binding?.apply {
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
                                            getString(
                                                R.string.dish_time_ready,
                                                food.readyInMinutes.toString()
                                            )
                                        tvDishServings.text =
                                            getString(R.string.dish_servings, food.servings.toString())
                                        tvDishPrice.text =
                                            getString(
                                                R.string.dish_dollar,
                                                food.pricePerServing.toString()
                                            )
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
                                        ivFavorDish.setOnClickListener {
                                            if (food.favoriteDish) {
                                                //foodViewModel.delete(food)
                                                foodViewModel.onEvent(FoodEvent.DeleteDish(food))
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
                                                Log.d(
                                                    "TAG",
                                                    "show recep in fragment " + recepFromIdList
                                                )
                                                //foodViewModel.insert(recepFromIdList!!)
                                                foodViewModel.onEvent(
                                                    FoodEvent.SaveFavFood(
                                                        recepFromIdList!!
                                                    )
                                                )
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
                            }
                            is Resource.Loading -> {
                                showCustomDialog()
                            }
                            is Resource.Error -> {
                                binding?.root?.let { showSnack ->
                                    com.google.android.material.snackbar.Snackbar.make(
                                        showSnack,
                                        "Some thing went wrong",
                                        com.google.android.material.snackbar.Snackbar.LENGTH_LONG
                                    ).show()
                                }
                            }
                        }
                    }
                }
            }
        }
    }


    private fun bottomSheet() {
//        val dialog = BottomSheetDialog(requireActivity())
//        val view = layoutInflater.inflate(R.layout.bottom_sheet_dialog, null)
//        val btnClose = view.findViewById<Button>(R.id.idBtnDismiss)
//        btnClose.setOnClickListener {
//            dialog.dismiss()
//        }
//        //dialog.setCancelable(false)
//        dialog.setContentView(view)
//        dialog.show()

        // there is the best way for shared in below
        val type = "text/plain"
        val subject = "Checkout this dish recipe"
        var extraText = ""
        val shareWith = "Share with"
        recepList?.let {
            var image = ""
            if (it.imageType == "Online"){
                image = it.image!!
            }
            var cookingInstructions = ""
            // The instruction or you can say the Cooking direction text is in the HTML format so we will you the fromHtml to populate it in the TextView.
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                cookingInstructions = Html.fromHtml(
                    it.instructions,
                    Html.FROM_HTML_MODE_COMPACT
                ).toString()
            } else {
                @Suppress("DEPRECATION")
                cookingInstructions = Html.fromHtml(it.instructions).toString()
            }
            extraText =
                "$image \n" +
                        "\n Title:  ${it.title} \n\n Type: ${it.sourceName} \n\n Category: $category" +
                        "\n\n summary: \n ${it.summary} \n\n Instructions To Cook: \n $cookingInstructions" +
                        "\n\n Time required to cook the dish approx ${it.readyInMinutes} minutes."
        }
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = type
        intent.putExtra(Intent.EXTRA_SUBJECT, subject)
        intent.putExtra(Intent.EXTRA_TEXT, extraText)
        startActivity(Intent.createChooser(intent, shareWith))
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
        binding = null
        recepFromIdList = null
    }
}