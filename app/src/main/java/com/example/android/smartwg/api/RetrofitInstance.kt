package com.example.android.smartwg.api

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import com.example.android.smartwg.util.Constants.Companion.BASE_URL

object RetrofitInstance {
    private val retrofit by lazy{
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    val api: APIService by lazy{
        retrofit.create(APIService::class.java)
    }

    val gson by lazy{
        GsonBuilder().setLenient().create()
    }
}