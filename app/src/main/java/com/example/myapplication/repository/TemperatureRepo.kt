package com.example.myapplication.repository

import androidx.lifecycle.LiveData
import com.example.myapplication.api.ApiResponse
import com.example.myapplication.api.RetrofitClient
import com.example.myapplication.api.TemperatureService
import com.example.myapplication.vo.temperature.TemperatureResponse

object TemperatureRepo {

    private val apiService: TemperatureService = RetrofitClient.apiService

    fun loadTemperatureForecast(): LiveData<ApiResponse<TemperatureResponse>> =
        apiService.getTemperatureForecast()

}