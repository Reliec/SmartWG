package com.example.android.smartwg.api

import com.example.android.smartwg.model.Highscore
import com.example.android.smartwg.model.User
import retrofit2.Response
import retrofit2.http.*

interface APIService {

    @GET("/USERS")
    suspend fun getUsers(): Response<List<User>>

    @FormUrlEncoded
    @POST("/LOGINFO")
    suspend fun getUserWherePassword(
        @Field("EMAIL") EMAIL: String,
        @Field("PASSWORD") PASSWORD: String
    ): Response<List<User>>

    @FormUrlEncoded
    @POST("/NEWUSER")
    suspend fun createNewUser(
        @Field("FIRST_NAME") FIRST_NAME: String,
        @Field("NAME") NAME: String,
        @Field("EMAIL") EMAIL: String,
        @Field("PASSWORD") PASSWORD: String,
        @Field("SACode") SACode: Int
    ): Response<String>

    @FormUrlEncoded
    @POST("/GETHIGHSCORESOFWG")
    suspend fun getHighscoreOfWG(
        @Field("SACODE") SACODE: Int?,
        @Field("ROOM") ROOM: String
    ): Response<List<Highscore>>

    @FormUrlEncoded
    @POST("/NEWHIGHSCORE")
    suspend fun createNewHighscore(
        @Field("USERID") USERID: Int?,
        @Field("SACODE") SACODE: Int?, @Field("DATE") DATE: String?,
        @Field("ROOM") ROOM: String?,
        @Field("FIRST_NAME") FIRST_NAME: String?, @Field("NAME") NAME: String?
    ): Response<String>

}