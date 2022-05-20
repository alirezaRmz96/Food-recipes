package com.example.food.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.food.R
import com.example.food.databinding.FragmentSwipeBinding
import com.google.android.material.composethemeadapter.MdcTheme


class SwipeFragment : Fragment() {
    private lateinit var mBinding: FragmentSwipeBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_swipe, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding = FragmentSwipeBinding.bind(view)
        val greeting = mBinding.greeting
        greeting.setContent {
            MdcTheme {
                GettingReady()
            }
        }
    }

}

@Preview
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
private fun GettingReady() {
    Scaffold(Modifier.padding(10.dp)) {
        Column {
            Text(
                text = "Your Favorite Recipes",
                modifier = Modifier.padding(3.dp),
                color = Color.Black,
                fontSize = 20.sp,
                fontStyle = FontStyle.Italic
            )
//            LazyVerticalGrid(
//                columns = androidx.compose.foundation.lazy.grid.GridCells.Adaptive(
//                    minSize = 180.dp
//                ), content = LazyGridScope.items()
//            )
        }
    }


}