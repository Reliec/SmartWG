package com.example.android.smartwg.repository

import com.example.android.smartwg.api.RetrofitInstance
import com.example.android.smartwg.model.User
import retrofit2.Response

class Repository {
    suspend fun getUsers(): Response<List<User>> {
        return RetrofitInstance.api.getUsers()
    }
}