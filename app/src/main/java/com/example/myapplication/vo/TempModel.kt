package com.example.myapplication.vo

import com.example.myapplication.vo.temperature.Time

data class TempModel(
    val data: Any,
    val type: Int
) {
    companion object {
        const val typeA: Int = 1
        const val typeB: Int = 2
    }
}