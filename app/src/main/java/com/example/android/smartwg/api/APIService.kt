package com.example.android.smartwg.api

import com.example.android.smartwg.model.User
import retrofit2.Response
import retrofit2.http.*

interface APIService {

    @GET("/USERS")
    suspend fun getUsers(): Response<List<User>>

}