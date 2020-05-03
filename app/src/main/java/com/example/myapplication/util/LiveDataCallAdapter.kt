package com.example.myapplication.util

import androidx.lifecycle.LiveData
import com.example.myapplication.api.ApiResponse
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Callback
import retrofit2.Response

import java.lang.reflect.Type
import java.util.concurrent.atomic.AtomicBoolean

class LiveDataCallAdapter<T>(private val responseType: Type) :
    CallAdapter<T, LiveData<ApiResponse<T>>> {

    override fun responseType() = responseType

    override fun adapt(call: Call<T>): LiveData<ApiResponse<T>> {
        return object : LiveData<ApiResponse<T>>() {
            private var started = AtomicBoolean(false)
            override fun onActive() {
                super.onActive()
                // 確定至少執行一次
                if (started.compareAndSet(false, true)) {
                    call.enqueue(object : Callback<T> {
                        override fun onResponse(call: Call<T>, response: Response<T>) {
                            println("body為 $response")
                            postValue(ApiResponse.create(response))
                        }
                        override fun onFailure(call: Call<T>, throwable: Throwable) {
                            postValue(ApiResponse.create(throwable))
                        }
                    })
                }
            }
        }
    }
}