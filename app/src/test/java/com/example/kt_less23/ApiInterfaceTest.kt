package com.example.kt_less23

import com.example.kt_less23.api_service.ApiInterface
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertNotNull
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiInterfaceTest {

    @Test
    fun testApiInterface() {
        val apiInterface = createApiInterface()

        assertNotNull(apiInterface)
    }

    private fun createApiInterface(): ApiInterface {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.coincap.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(ApiInterface::class.java)
    }
}