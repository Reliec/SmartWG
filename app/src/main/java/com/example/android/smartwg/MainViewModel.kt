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

    fun getUserDataViewM(){
        viewModelScope.launch {
            val response = repository.getUsers()
            userListResponse.value = response
        }
    }
}