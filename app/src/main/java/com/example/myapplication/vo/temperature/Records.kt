package com.example.myapplication.vo.temperature

import com.google.gson.annotations.SerializedName


data class Records(

    @SerializedName("datasetDescription") val dataSetDescription: String,
    @SerializedName("location") val location: List<Location>
)