package com.example.kt_less23.api_service

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {
    @GET("/v2/rates/{cryptoName}")
    suspend fun getCryptoByName(
        @Path("cryptoName") name: String
    ): Response <Model>
}