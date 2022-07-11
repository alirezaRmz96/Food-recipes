package com.example.food.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.example.food.R
import com.example.food.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var mBinding : ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)




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
    fun <T : ViewModel> getSharedViewModel(viewModel: Class<T>): T {
        return ViewModelProvider(this).get(viewModel)
    }

    fun hideBottomNavigationView(){
        mBinding.navView.clearAnimation()
        mBinding.navView.animate().translationY(mBinding.navView.height.toFloat()).duration = 300
        mBinding.navView.visibility = View.GONE
    }

    fun showBottomNavigationView() {
        mBinding.navView.clearAnimation()
        mBinding.navView.animate().translationY(0f).duration = 300
        mBinding.navView.visibility = View.VISIBLE
    }
}