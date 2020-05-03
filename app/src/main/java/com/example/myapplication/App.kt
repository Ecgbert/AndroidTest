package com.example.myapplication

import android.app.Application
import com.example.myapplication.util.SharedPreferencesHelper

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        SharedPreferencesHelper.init(this)
    }
}