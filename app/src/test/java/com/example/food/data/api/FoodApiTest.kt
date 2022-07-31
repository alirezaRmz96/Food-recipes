package com.example.food.data.api

import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FoodApiTest {
    private lateinit var service:FoodApi
    private lateinit var server: MockWebServer

    @Before
    fun setUp() {
       server = MockWebServer()
        service = Retrofit.Builder()
            .baseUrl(server.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FoodApi::class.java)
    }
    /**
     * because server can't read json we need to create json reader
     * */
    private fun enqueueMockResponse(
        fileName:String
    ){
        val inputStream = javaClass.classLoader!!.getResourceAsStream(fileName)
        val source = inputStream.source().buffer()
        val mockResponse = MockResponse()
        mockResponse.setBody(source.readString(Charsets.UTF_8))
        server.enqueue(mockResponse)
    }

    @Test
    fun getSpecialFood_sentRequest_receivedExpected(){
        runBlocking {
            enqueueMockResponse("SpecialFood.json")
            val responseBody = service.getAllFood(ingredients = "Caramel")
            val request = server.takeRequest()
            assertThat(responseBody).isNotNull()
            assertThat(request.path).isEqualTo("/findByIngredients?apiKey=b7789ce495454fa281f4d2500f9bcb53&ingredients=Caramel&number=10")
        }
    }



    @After
    fun tearDown() {
        server.shutdown()
    }

}