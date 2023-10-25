package com.example.kt_less23


import com.example.kt_less23.api_service.ApiClient
import com.example.kt_less23.api_service.ApiInterface
import com.example.kt_less23.api_service.Data
import com.example.kt_less23.api_service.Model

import retrofit2.Response


class Repository(private val client: ApiClient) {
    suspend fun getCrypto(name: String): Response<Model> {
        val apiInterface = client.client.create(ApiInterface::class.java)
        val response = apiInterface.getCryptoByName(name)

        if (response.isSuccessful) {
            val model = response.body()
            if (model != null) {
                val data = model.data
                if (data != null) {
                    val dataId = data.id
                    val dataRate = data.rateUsd
                    val symbol = data.currencySymbol
                    return Response.success(Model(data = Data(id = dataId, rateUsd = dataRate, currencySymbol = symbol)))
                }
            }
        }
        return response
    }
}