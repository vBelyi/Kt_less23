package com.example.kt_less23

import com.example.kt_less23.api_service.ApiClient
import org.junit.Test
import org.junit.Assert.assertNotNull

class ApiClientTest {

    @Test
    fun testApiClientCreation() {
        val apiClient = ApiClient()
        val client = apiClient.client

        assertNotNull(client)
    }
}