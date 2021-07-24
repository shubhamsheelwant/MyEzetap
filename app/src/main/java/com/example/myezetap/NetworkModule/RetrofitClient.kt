package com.example.myezetap.NetworkModule

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


internal object RetrofitClientInstance {
    private var retrofit: Retrofit? = null
    private const val BASE_URL = "https://demo.ezetap.com/"
    val retrofitInstance: Retrofit?
        get() {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit
        }
}