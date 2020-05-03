package com.example.myapplication.vo.temperature

import com.google.gson.annotations.SerializedName


data class Parameter(

    @SerializedName("parameterName") val parameterName: Int,
    @SerializedName("parameterUnit") val parameterUnit: String
)