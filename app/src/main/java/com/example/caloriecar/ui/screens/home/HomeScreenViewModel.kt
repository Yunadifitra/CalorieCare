package com.example.caloriecar.ui.screens.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.caloriecar.data.UserRepository
import com.example.caloriecar.data.model.User
import kotlinx.coroutines.launch

class HomeScreenViewModel(private val repository: UserRepository): ViewModel() {
    var foodTitle = mutableStateOf("")

    fun logout() {
        viewModelScope.launch {
            repository.logout()
        }
    }

    fun getSession(): LiveData<User> {
        return repository.getSession().asLiveData()
    }
}