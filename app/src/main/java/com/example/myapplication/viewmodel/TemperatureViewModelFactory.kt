package com.example.myapplication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.repository.TemperatureRepo
import com.example.myapplication.ui.temperature.TemperatureViewModel
import java.lang.IllegalArgumentException

class TemperatureViewModelFactory(private val temperatureRepo: TemperatureRepo) :
    ViewModelProvider.Factory {


    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TemperatureViewModel::class.java)) {
            return TemperatureViewModel(temperatureRepo) as T
        }
        throw IllegalArgumentException("unknown ViewModel class")
    }
}