package com.example.android.smartwg

import android.text.Editable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.smartwg.model.*
import com.example.android.smartwg.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

/**
 * Class that contains Mutable live data that is send from the database and can be used in the appl
 * ication. Its also the first layer to call the Endpoints from Repository.
 *
 * @property repository
 */
class MainViewModel(private val repository: Repository): ViewModel() {


    var echoStringResponse: MutableLiveData<Response<String>> = MutableLiveData()

    var userListResponse: MutableLiveData<Response<List<User>>> = MutableLiveData()
    var userWherePasswordResponse: MutableLiveData<Response<List<User>>> = MutableLiveData();
    var highscoreResponse: MutableLiveData<Response<List<Highscore>>> = MutableLiveData()
    var shoppingListResponse: MutableLiveData<Response<List<ShoppingList>>> = MutableLiveData()
    val shoppingListItemsResponse: MutableLiveData<Response<List<ShoppingListItem>>> = MutableLiveData()
    var toiletStatusResponse: MutableLiveData<Response<List<ToiletStatus>>> = MutableLiveData()
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
            echoStringResponse.value = response
        }
    }

    fun deleteShoppingListFromUserViewM(USERID: Int?, SHOPPINGLISTID: Int?) {
        viewModelScope.launch {
            val response = repository.deleteShoppingListFromUserRepo(USERID, SHOPPINGLISTID)
            echoStringResponse.value = response
        }
    }

    fun updateSettingsViewM(oldSACODE: Int?, newSACODE:Int?, oldEMAIL: String?, newEMAIL:String?, oldPASSWORD: String?, newPASSWORD:String?){
        viewModelScope.launch {
            val response = repository.updateSettingsRepo(oldSACODE, newSACODE, oldEMAIL, newEMAIL, oldPASSWORD, newPASSWORD)
            echoStringResponse.value = response
        }
    }

    fun updateWGBSViewM(oldWGBs:String?, newWGBs:String?,oldTitle:String?, newTitle:String?, SACODE: Int?){
        viewModelScope.launch {
            val response = repository.updateWGBSRepo(oldWGBs, newWGBs,oldTitle,newTitle, SACODE)
            echoStringResponse.value = response
        }
    }

    fun updateShoppingListViewM(userID: Int?, shoppingListID: Int, tvTitle: Editable) {
        viewModelScope.launch {
            val response = repository.updateShoppingListRepo(userID, shoppingListID, tvTitle)
            echoStringResponse.value = response
        }
    }

    fun getShoppingListItemsViewM(shoppingListID: Int) {
        viewModelScope.launch {
            val response = repository.getShoppingListItemsRepo(shoppingListID)
            shoppingListItemsResponse.value = response
        }
    }

    fun createShoppingListItem(shoppingListID: Int) {
        viewModelScope.launch {
            val response = repository.createShoppingListItemRepo(shoppingListID)
            echoStringResponse.value = response
        }
    }

    fun deleteShoppingListItem(shoppingListItemID: Int) {
        viewModelScope.launch {
            val response = repository.deleteShoppingListItemRepo(shoppingListItemID)
            echoStringResponse.value = response
        }
    }

    fun updateShoppingListItemViewModel(id: Int, newTitle: String, newAmount: String, newUnit: String) {
        viewModelScope.launch {
            val response = repository.updateShoppingListItemRepo(id, newTitle, newAmount, newUnit)
            echoStringResponse.value = response
        }
    }
}