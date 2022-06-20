package com.example.android.smartwg

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.smartwg.repository.Repository

/**
 * Factory Pattern create ViewModels. Used to send and receive data from the backend.
 *
 * @property repository
 */
class MainViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T
    }
}