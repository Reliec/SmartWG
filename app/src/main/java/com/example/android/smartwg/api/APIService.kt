package com.example.android.smartwg.api

import android.text.Editable
import android.widget.EditText
import com.example.android.smartwg.model.*
import retrofit2.Response
import retrofit2.http.*

interface APIService {

    @GET("/USERS")
    suspend fun getUsers(): Response<List<User>>

    @FormUrlEncoded
    @POST("/WGBS")
    suspend fun getWGBS(@Field("SACODE")SACODE: Int?): Response<List<WGB>>

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
        @Field("ROOM") ROOM: String,
        @Field("USERID")USERID: Int?
    ): Response<List<Highscore>>

    @FormUrlEncoded
    @POST("/NEWHIGHSCORE")
    suspend fun createNewHighscore(
        @Field("USERID") USERID: Int?,
        @Field("SACODE") SACODE: Int?, @Field("DATE") DATE: String?,
        @Field("ROOM") ROOM: String?,
        @Field("FIRST_NAME") FIRST_NAME: String?, @Field("NAME") NAME: String?
    ): Response<String>

    @FormUrlEncoded
    @POST("/GETTOILETSTATUS")
    suspend fun getToiletStatus(
        @Field("SACODE")SACODE: Int?)
    : Response<List<ToiletStatus>>

    @FormUrlEncoded
    @POST("/GETSHOPPINGLISTSFROMUSER")
    suspend fun getShoppingListsFromUser(
        @Field("AUTHORID") AUTHORID: Int?,
    ): Response<List<ShoppingList>>

    @FormUrlEncoded
    @POST("CREATESHOPPINGLISTFROMUSER")
    suspend fun createShoppingListFromUser(
        @Field("AUTHORID") AUTHORID: Int?
    ): Response<String>

    @FormUrlEncoded
    @PUT("UPDATESHOPPINGLISTFROMUSER")
    suspend fun updateShoppingListFromUser(
        @Field("AUTHORID") authorID: Int?,
        @Field("SHOPPINGLISTID") shoppingListID: Int,
        @Field("newTITLE") tvTitle: Editable
    ): Response<String>

    @FormUrlEncoded
    @HTTP(method ="DELETE",path = "DELETESHOPPINGLISTFROMUSER", hasBody = true)
    suspend fun deleteShoppingListFromUser(
        @Field("AUTHORID") AUTHORID: Int?,
        @Field("SHOPPINGLISTID") SHOPPINGLISTID: Int?
    ): Response<String>

    @FormUrlEncoded
    @PUT("UPDATESETTINGS")
    suspend fun updateSettings(
        @Field("oldSACODE")oldSACODE: Int ?, @Field("newSACODE") newSACODE: Int?,
        @Field("oldEMAIL")oldEMAIL:String?, @Field("newEMAIL")newEMAIL: String?,
        @Field("oldPASSWORD")oldPASSWORD: String?, @Field("newPASSWORD")newPASSWORD: String?
    ):Response<String>

    @FormUrlEncoded
    @PUT("UPDATEWGBS")
    suspend fun updateWGBS(
        @Field("oldWGBs")oldWGBs:String?, @Field("newWGBs")newWGBs:String?,
        @Field("oldTitle")oldTitle:String?, @Field("newTitle")newTitle:String?,
        @Field("SACode")SACode:Int?
    ):Response<String>

    @FormUrlEncoded
    @POST("GETSHOPPINGLISTITEMS")
    suspend fun getShoppingListItems(
        @Field("SHOPPINGLISTID")shoppingListID: Int
    ): Response<List<ShoppingListItem>>

    @FormUrlEncoded
    @POST("CREATESHOPPINGLISTITEM")
    suspend fun createShoppingListItem(
        @Field("SHOPPINGLISTID")shoppingListID: Int
    ): Response<String>

    @FormUrlEncoded
    @HTTP(method ="DELETE",path = "DELETESHOPPINGLISTITEM", hasBody = true)
    suspend fun deleteShoppingListItem(
        @Field("SHOPPINGLISTITEMID")shoppingListItemID: Int
    ): Response<String>

}