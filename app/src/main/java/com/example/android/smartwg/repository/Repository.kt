package com.example.android.smartwg.repository

import com.example.android.smartwg.api.RetrofitInstance
import com.example.android.smartwg.model.User
import retrofit2.Response

class Repository {
    suspend fun getUsersRepo(): Response<List<User>> {
        return RetrofitInstance.api.getUsers()
    }

    suspend fun getUserWherePasswordRepo(EMAIL: String, PASSWORD: String): Response<List<User>>{
        return RetrofitInstance.api.getUserWherePassword(EMAIL, PASSWORD)
    }

    suspend fun createNewUserRepo(FIRST_NAME:String, NAME:String, EMAIL: String, PASSWORD: String, SACode:Int): Response<String>{
        return RetrofitInstance.api.createNewUser(FIRST_NAME, NAME, EMAIL, PASSWORD, SACode)
    }
}