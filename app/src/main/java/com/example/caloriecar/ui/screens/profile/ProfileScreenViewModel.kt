package com.example.caloriecar.ui.screens.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.caloriecar.data.UserRepository
import kotlinx.coroutines.launch

class ProfileScreenViewModel(private val repository: UserRepository): ViewModel() {
    fun logout() {
        viewModelScope.launch {
            repository.logout()
        }
    }
}