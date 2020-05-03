package com.example.myapplication.vo.temperature

import com.google.gson.annotations.SerializedName

data class Location(

    @SerializedName("locationName") val locationName: String,
    @SerializedName("weatherElement") val weatherElement: List<WeatherElement>
)