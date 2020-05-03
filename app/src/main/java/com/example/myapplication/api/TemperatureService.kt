package com.example.myapplication.api

import androidx.lifecycle.LiveData
import com.example.myapplication.vo.Resource
import com.example.myapplication.vo.temperature.TemperatureResponse
import retrofit2.http.GET


interface TemperatureService {

    @GET("v1/rest/datastore/F-C0032-001?Authorization=CWB-7A7BCEAE-2431-4025-813C-145F39A7942D&locationName=臺北市&elementName=MinT")
    fun getTemperatureForecast() :LiveData<ApiResponse<TemperatureResponse>>

}