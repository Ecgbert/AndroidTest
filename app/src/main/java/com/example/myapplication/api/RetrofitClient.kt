package com.example.myapplication.api

import com.example.myapplication.util.LiveDataCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    val apiService: TemperatureService by lazy {
        val client = Retrofit.Builder()
            .baseUrl("https://opendata.cwb.gov.tw/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .build()
        return@lazy client.create(TemperatureService::class.java)
    }
}