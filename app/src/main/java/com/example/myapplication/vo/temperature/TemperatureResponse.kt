package com.example.myapplication.vo.temperature

import com.google.gson.annotations.SerializedName

data class TemperatureResponse(

    @SerializedName("success") val success: Boolean,
    @SerializedName("result") val result: Result,
    @SerializedName("records") val records: Records
)