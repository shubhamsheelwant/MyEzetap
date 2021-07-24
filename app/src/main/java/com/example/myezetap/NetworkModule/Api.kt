package com.example.myezetap.NetworkModule

import com.example.myezetap.Model.CustomUiResponse
import retrofit2.Call
import retrofit2.http.GET

interface Api {
    @GET("mobileapps/android_assignment")
    fun fetchCustomUI(): Call<CustomUiResponse?>
}