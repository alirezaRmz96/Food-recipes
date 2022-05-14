package com.example.food.ui.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.food.CoroutineTestRule
import com.example.food.data.model.allList.AllFoodList
import com.example.food.data.util.Resource
import com.example.food.domain.repository.FoodRepository
import com.example.food.domain.usecase.GetAllFoodUseCase
import com.example.food.domain.usecase.GetInformationFoodUseCase
import com.example.food.domain.usecase.GetRecepFromIdUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class FoodViewModelTest{
    @ExperimentalCoroutinesApi
    private val coroutineDispatcher = TestCoroutineDispatcher()

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutineTestRule = CoroutineTestRule(coroutineDispatcher)

     @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: FoodViewModel
    private lateinit var foodRepository: FoodRepository
//    private lateinit var isNetWorking: IsNetWorking

    @Before
     fun setUp() {
        foodRepository = mockk()
//        isNetWorking = mockk()
        val getAllFoodUseCase = GetAllFoodUseCase(foodRepository)
        val getInformationFoodUseCase = GetInformationFoodUseCase(foodRepository)
        val getRecepFromIdUseCase = GetRecepFromIdUseCase(foodRepository)
        viewModel = FoodViewModel(
            getAllFoodUseCase,
            getInformationFoodUseCase,
//            isNetWorking,
            getRecepFromIdUseCase
        )
    }



    @ExperimentalCoroutinesApi
    @Test
    fun `test Food ViewModel for all food`() =
        coroutineTestRule.coroutineScope.runBlockingTest {
            val response = ArrayList<AllFoodList>()
            coEvery {  foodRepository.getAllFoodRecep()}coAnswers {
                delay(100)
                firstArg<(ArrayList<AllFoodList>)-> Resource<AllFoodList>>().invoke(response)
            }
        }
        val allFood = viewModel.foodLiveData.observeForever {  }

}