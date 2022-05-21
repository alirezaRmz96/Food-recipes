package com.example.food.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.asFlow
import com.example.food.R
import com.example.food.data.model.receFromId.RecepFromIdList
import com.example.food.data.util.component.FavoriteDishCard
import com.example.food.databinding.FragmentSwipeBinding
import com.example.food.ui.MainActivity
import com.example.food.ui.viewModel.FoodDBViewModel
import com.google.android.material.composethemeadapter.MdcTheme

class SwipeFragment : Fragment() {
    private lateinit var mBinding: FragmentSwipeBinding
   private var mFavoriteList1 : List<RecepFromIdList> = listOf()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_swipe, container, false)
    }
    private val foodDBViewModel : FoodDBViewModel by lazy {
        (activity as MainActivity?)!!.getSharedViewModel(FoodDBViewModel::class.java)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding = FragmentSwipeBinding.bind(view)
        val greeting = mBinding.greeting

        foodDBViewModel.favoriteDishes.observe(viewLifecycleOwner){mFavoriteList->
            greeting.setContent {
                /** here i can use fav and mFavorite to pass data for favorite list
                 *  but fav use collectasAstate an that's only use in Composable
                 * */
                val fav = foodDBViewModel.movieList.collectAsState().value
                Log.d("TAG", "onViewCreated: " + fav)
                MdcTheme {
                    GettingReady(mFavoriteList)
                }
            }
        }


    }

}


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
private fun GettingReady(
    mList : List<RecepFromIdList>
) {
    Scaffold(Modifier.padding(10.dp)) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                textAlign = TextAlign.Center,
                text = "Your Favorite Recipes",
                color = Color.Black,
                fontSize = 20.sp,
                fontStyle = FontStyle.Italic,
                modifier = Modifier.fillMaxWidth()
            )
            LazyVerticalGrid(
                columns = androidx.compose.foundation.lazy.grid.GridCells.Adaptive(
                    minSize = 180.dp,
                ),
                contentPadding = PaddingValues(
                    start = 12.dp,
                    end = 12.dp,
                ),
            ){
                items(mList){ mList->
                    FavoriteDishCard(mList = mList)
                }
            }
        }
    }


}