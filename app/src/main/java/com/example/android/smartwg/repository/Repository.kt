package com.example.android.smartwg.repository

import com.example.android.smartwg.api.RetrofitInstance
import com.example.android.smartwg.model.User
import com.example.android.smartwg.model.Highscore
import com.example.android.smartwg.model.ShoppingList
import com.example.android.smartwg.model.ToiletStatus
import retrofit2.Response

class Repository {
    suspend fun getUsersRepo(): Response<List<User>> {
        return RetrofitInstance.api.getUsers()
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

    suspend fun updateSettingsRepo(oldSACODE: Int?, newSACODE:Int?, oldEMAIL: String?, newEMAIL:String?, oldPASSWORD: String?, newPASSWORD:String?):Response<String>{
        return RetrofitInstance.api.updateSettings(oldSACODE, newSACODE, oldEMAIL, newEMAIL, oldPASSWORD, newPASSWORD)
    }
}
