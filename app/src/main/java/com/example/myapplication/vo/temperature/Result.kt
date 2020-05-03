package com.example.myapplication.vo.temperature

import com.google.gson.annotations.SerializedName

data class Result(

    @SerializedName("resource_id") val resource_id: String,
    @SerializedName("fields") val fields: List<Fields>
)