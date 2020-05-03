package com.example.myapplication.vo.temperature

import com.google.gson.annotations.SerializedName

data class Fields(

    @SerializedName("id") val id: String,
    @SerializedName("type") val type: String
)