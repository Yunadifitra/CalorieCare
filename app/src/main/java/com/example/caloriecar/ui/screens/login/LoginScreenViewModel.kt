package com.example.caloriecar.ui.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.caloriecar.data.UserRepository
import com.example.caloriecar.data.model.User
import kotlinx.coroutines.launch

class LoginScreenViewModel(private val repository: UserRepository) : ViewModel() {
    fun saveSession(user: User) {
        viewModelScope.launch {
            repository.saveSession(user)
        }
    }
}