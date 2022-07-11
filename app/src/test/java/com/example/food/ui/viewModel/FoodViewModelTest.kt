package com.example.food.ui.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.food.CoroutineTestRule
import com.example.food.domain.repository.FoodRepository
import com.example.food.domain.usecase.GetInformationFoodUseCase
import com.example.food.domain.usecase.GetRecepFromIdUseCase
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import junit.framework.Assert.*
import kotlinx.coroutines.test.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class FoodViewModelTest{
    //use StandardTestDispatcher instead TestCoroutineDispatcher
    @ExperimentalCoroutinesApi
    private val coroutineDispatcher = TestCoroutineDispatcher()

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutineTestRule = CoroutineTestRule(coroutineDispatcher)

     @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: FoodViewModel
    private lateinit var foodRepository: FoodRepository

    @Before
     fun setUp() {
        foodRepository = mockk()
        val getAllFoodUseCase = GetAllFoodUseCase(foodRepository)
        val getInformationFoodUseCase = GetInformationFoodUseCase(foodRepository)
        val getRecepFromIdUseCase = GetRecepFromIdUseCase(foodRepository)
        viewModel = FoodViewModel(
            getAllFoodUseCase,
            getInformationFoodUseCase,
            getRecepFromIdUseCase,
            foodRepository
        )
    }

    @ExperimentalCoroutinesApi
    @Test
    fun test() = runTest{

    }

//    @ExperimentalCoroutinesApi
//    @Test
//    fun `test getting data with error response should change live data state to error`() =
//        coroutineTestRule.coroutineScope.runBlockingTest {
//            val returnError = Throwable()
//            coEvery { foodRepository.getAllFoodRecep() } throws (returnError)
//            viewModel.foodLiveData.observeForever {
//                println("users result $it $currentTime")
//            }
//            viewModel.getAllFood()
//
////            assertNotNull(viewModel.foodLiveData.value)
//            assertEquals(viewModel.foodLiveData.value,
//                Resource.Error(returnError.message.toString(),null))
//        }
}