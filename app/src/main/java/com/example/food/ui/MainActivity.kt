package com.example.food.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.food.R
import com.example.food.databinding.ActivityMainBinding
import com.example.food.ui.viewModel.FoodViewModel
import com.example.food.ui.viewModel.FoodViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: FoodViewModelFactory
    lateinit var foodViewModel: FoodViewModel
    private lateinit var mBinding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        foodViewModel = ViewModelProvider(this,factory)
            .get(FoodViewModel::class.java)

        val navView:BottomNavigationView = mBinding.navView

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController

        val  appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.homeFragment, R.id.swipeFragment, R.id.profileFragment
            )
        )
       //setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
}