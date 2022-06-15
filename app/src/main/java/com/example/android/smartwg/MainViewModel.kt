package com.example.android.smartwg

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.smartwg.model.*
import com.example.android.smartwg.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response
import java.util.*

class MainViewModel(private val repository: Repository): ViewModel() {

    var userListResponse: MutableLiveData<Response<List<User>>> = MutableLiveData()
    var userWherePasswordResponse: MutableLiveData<Response<List<User>>> = MutableLiveData();
    var echoStringResponse: MutableLiveData<Response<String>> = MutableLiveData()
    var highscoreResponse: MutableLiveData<Response<List<Highscore>>> = MutableLiveData()
    var shoppingListResponse: MutableLiveData<Response<List<ShoppingList>>> = MutableLiveData()
    var toiletStatusResponse: MutableLiveData<Response<List<ToiletStatus>>> = MutableLiveData()
    var createShoppingListResponse: MutableLiveData<Response<List<ShoppingList>>> = MutableLiveData()
    var deleteShoppingListResponse: MutableLiveData<Response<List<ShoppingList>>> = MutableLiveData()
    var wgbStringResponse: MutableLiveData<Response<List<WGB>>> = MutableLiveData()

    fun getUsersViewM(){
        viewModelScope.launch {
            val response = repository.getUsersRepo()
            userListResponse.value = response
        }
    }

    fun getWGBSViewM(SACODE: Int?){
        viewModelScope.launch {
            val response = repository.getWGBSRepo(SACODE)
            wgbStringResponse.value = response
        }
    }

    fun getUserWherePasswordViewM(EMAIL: String, PASSWORD: String){
        viewModelScope.launch {
            val response = repository.getUserWherePasswordRepo(EMAIL, PASSWORD)
            userWherePasswordResponse.value = response
        }
    }

    fun createNewUserViewM(FIRST_NAME:String, NAME:String, EMAIL: String, PASSWORD: String, SACode:Int){
        viewModelScope.launch {
            val response = repository.createNewUserRepo(FIRST_NAME, NAME, EMAIL, PASSWORD, SACode)
            echoStringResponse.value = response
        }
    }

    fun getHighscoresOfWGRepoViewM(SACODE: Int?, ROOM :String, USERID : Int?){
        viewModelScope.launch {
            val repsonse = repository.getHighscoreOfWGRepo(SACODE, ROOM, USERID)
            highscoreResponse.value = repsonse
        }
    }

    fun createNewHighscoreViewM(USERID: Int?, SACODE: Int?, DATE:String?, ROOM: String?, FIRST_NAME: String?, NAME: String?){
        viewModelScope.launch {
            val response = repository.createNewHighscoreRepo(USERID, SACODE, DATE, ROOM, FIRST_NAME, NAME)
            echoStringResponse.value = response
        }
    }

    fun getToiletStatusViewM(SACODE: Int?){
        viewModelScope.launch {
            val response = repository.getToiletStatus(SACODE)
            toiletStatusResponse.value = response
        }
    }

    fun getShoppingListsFromUserViewM(USERID: Int?) {
        viewModelScope.launch {
            val response = repository.getShoppingListsFromUserRepo(USERID)
            shoppingListResponse.value = response
        }
    }

    fun createShoppingListFromUserViewM(USERID: Int?) {
        viewModelScope.launch {
            val response = repository.createShoppingListFromUserRepo(USERID)
            createShoppingListResponse.value = response
        }
    }

    fun deleteShoppingListFromUserViewM(USERID: Int?, SHOPPINGLISTID: Int?) {
        viewModelScope.launch {
            val response = repository.deleteShoppingListFromUserRepo(USERID, SHOPPINGLISTID)
            deleteShoppingListResponse.value = response
        }
    }

    fun updateSettingsViewM(oldSACODE: Int?, newSACODE:Int?, oldEMAIL: String?, newEMAIL:String?, oldPASSWORD: String?, newPASSWORD:String?){
        viewModelScope.launch {
            val response = repository.updateSettingsRepo(oldSACODE, newSACODE, oldEMAIL, newEMAIL, oldPASSWORD, newPASSWORD)
            echoStringResponse.value = response
        }
    }

    fun updateWGBSViewM(oldWGBs:String?, newWGBs:String?, SACODE: Int?){
        viewModelScope.launch {
            val response = repository.updateWGBSRepo(oldWGBs, newWGBs, SACODE)
            echoStringResponse.value = response
        }
    }
}