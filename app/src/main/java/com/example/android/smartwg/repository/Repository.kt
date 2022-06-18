package com.example.android.smartwg.repository

import android.text.Editable
import com.example.android.smartwg.api.RetrofitInstance
import com.example.android.smartwg.model.*
import retrofit2.Response
import retrofit2.Retrofit

class Repository {
    suspend fun getUsersRepo(): Response<List<User>> {
        return RetrofitInstance.api.getUsers()
    }

    suspend fun getWGBSRepo(SACODE: Int?): Response<List<WGB>>{
        return RetrofitInstance.api.getWGBS(SACODE)
    }

    suspend fun getUserWherePasswordRepo(EMAIL: String, PASSWORD: String): Response<List<User>> {
        return RetrofitInstance.api.getUserWherePassword(EMAIL, PASSWORD)
    }

    suspend fun createNewUserRepo(
        FIRST_NAME: String,
        NAME: String,
        EMAIL: String,
        PASSWORD: String,
        SACode: Int
    ): Response<String> {
        return RetrofitInstance.api.createNewUser(FIRST_NAME, NAME, EMAIL, PASSWORD, SACode)
    }

    suspend fun getHighscoreOfWGRepo(SACODE: Int?, ROOM: String, USERID: Int?): Response<List<Highscore>> {
        return RetrofitInstance.api.getHighscoreOfWG(SACODE, ROOM, USERID)
    }

    suspend fun createNewHighscoreRepo(
        USERID: Int?,
        SACODE: Int?,
        DATE: String?,
        ROOM: String?,
        FIRST_NAME: String?,
        NAME: String?
    ): Response<String> {
        return RetrofitInstance.api.createNewHighscore(USERID, SACODE, DATE, ROOM, FIRST_NAME, NAME)
    }

    suspend fun getToiletStatus(
        SACODE: Int?
    ):Response<List<ToiletStatus>>{
        return RetrofitInstance.api.getToiletStatus(SACODE)
    }

    suspend fun getShoppingListsFromUserRepo(USERID: Int?): Response<List<ShoppingList>> {
        return RetrofitInstance.api.getShoppingListsFromUser(USERID)
    }

    suspend fun createShoppingListFromUserRepo(USERID: Int?): Response<String> {
        return RetrofitInstance.api.createShoppingListFromUser(USERID)
    }

    suspend fun deleteShoppingListFromUserRepo(USERID: Int?, SHOPPINGLISTID: Int?): Response<String> {
        return RetrofitInstance.api.deleteShoppingListFromUser(USERID, SHOPPINGLISTID)
    }

    suspend fun updateSettingsRepo(oldSACODE: Int?, newSACODE:Int?, oldEMAIL: String?, newEMAIL:String?, oldPASSWORD: String?, newPASSWORD:String?):Response<String>{
        return RetrofitInstance.api.updateSettings(oldSACODE, newSACODE, oldEMAIL, newEMAIL, oldPASSWORD, newPASSWORD)
    }

    suspend fun updateWGBSRepo(oldWGBs:String?, newWGBs:String?,oldTitle:String?,newTitle:String?, SACODE: Int?):Response<String>{
        return RetrofitInstance.api.updateWGBS(oldWGBs, newWGBs, oldTitle, newTitle, SACODE)
    }

    suspend fun updateShoppingListRepo(userID: Int?, shoppingListID: Int, tvTitle: Editable): Response<String>? {
        return RetrofitInstance.api.updateShoppingListFromUser(userID,shoppingListID, tvTitle)
    }

    suspend fun getShoppingListItemsRepo(shoppingListID: Int): Response<List<ShoppingListItem>> {
        return RetrofitInstance.api.getShoppingListItems(shoppingListID)
    }

    suspend fun createShoppingListItemRepo(shoppingListID: Int): Response<String>? {
        return RetrofitInstance.api.createShoppingListItem(shoppingListID)
    }

    suspend fun deleteShoppingListItemRepo(shoppingListItemID: Int): Response<String>? {
        return RetrofitInstance.api.deleteShoppingListItem(shoppingListItemID)
    }

    suspend fun updateShoppingListItemRepo(id: Int, newTitle: String, newAmount: String, newUnit: String): Response<String>? {
        return RetrofitInstance.api.updateShoppingListItem(id, newTitle, newAmount, newUnit)
    }
}
