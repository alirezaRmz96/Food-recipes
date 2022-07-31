package com.example.food.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.work.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.example.food.R
import com.example.food.data.repository.workManager.RefreshLatestFoodWorker
import com.example.food.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
//        val oneTimeRequestConstraints = Constraints.Builder()
//            .setRequiresCharging(false)
//            .setRequiredNetworkType(NetworkType.CONNECTED)
//            .build()
//        val data = Data.Builder()
//        data.putString("inputKey", "ingredients")
//        val sampleWork = OneTimeWorkRequest
//            .Builder(RefreshLatestFoodWorker::class.java)
//            .setInputData(data.build())
//            .setConstraints(oneTimeRequestConstraints)
//            .build()
//        WorkManager.getInstance(this@MainActivity).enqueue(sampleWork)
//        WorkManager.getInstance(this@MainActivity).getWorkInfoByIdLiveData(sampleWork.id)
//            .observe(this, Observer { workInfo ->
//                if (workInfo != null) {
//                    when (workInfo.state) {
//                        WorkInfo.State.ENQUEUED -> {
//                            Log.d("MyTAG", "getLatestFood: Task enqueued.")
//                        }
//                        WorkInfo.State.BLOCKED -> {
//                            Log.d("MyTAG", "getLatestFood: Task blocked.")
//                        }
//                        WorkInfo.State.RUNNING -> {
//                            Log.d("MyTAG", "getLatestFood: Task running.")
//                        }
//                        else -> {
//                            Log.d("MyTAG", "getLatestFood: Task state isFinished else part.")
//                        }
//                    }
//                }
//                if (workInfo != null && workInfo.state.isFinished) {
//                    when (workInfo.state) {
//                        WorkInfo.State.SUCCEEDED ->{
//                            Log.d("MyTAG", "getLatestFood: Task successful.")
//                            val successOutputData = workInfo.outputData
//                            val outputText = successOutputData.getString("outputKey")
//                            Log.d("MyTAG", "getLatestFood: $outputText")
//                        }
//                        WorkInfo.State.FAILED -> {
//                            Log.d("MyTAG", "getLatestFood: Task Failed")
//                        }
//                        WorkInfo.State.CANCELLED -> {
//                            Log.d("MyTAG", "getLatestFood: Task cancelled")
//                        }
//                        else ->{
//                            Log.d("MyTAG", "getLatestFood isFinished: Task state isFinished else part.")
//                        }
//                    }
//                }
//            })


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