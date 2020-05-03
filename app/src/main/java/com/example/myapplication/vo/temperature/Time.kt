package com.example.myapplication.vo.temperature

import com.google.gson.annotations.SerializedName

data class Time(
    @SerializedName("startTime") val startTime: String,
    @SerializedName("endTime") val endTime: String,
    @SerializedName("parameter") val parameter: Parameter
)