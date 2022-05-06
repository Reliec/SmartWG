package com.example.android.smartwg

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.smartwg.model.User
import com.example.android.smartwg.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: Repository): ViewModel() {

    var userListResponse: MutableLiveData<Response<List<User>>> = MutableLiveData()
    var userWherePasswordResponse: MutableLiveData<Response<List<User>>> = MutableLiveData();
    var echoStringResponse: MutableLiveData<Response<String>> = MutableLiveData()

    fun getUsersViewM(){
        viewModelScope.launch {
            val response = repository.getUsersRepo()
            userListResponse.value = response
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
}