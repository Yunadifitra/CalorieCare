package com.example.caloriecar.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.caloriecar.data.UserRepository
import com.example.caloriecar.ui.screens.add.AddScreenViewModel
import com.example.caloriecar.ui.screens.home.HomeScreenViewModel
import com.example.caloriecar.ui.screens.login.LoginScreenViewModel
import com.example.caloriecar.ui.screens.profile.ProfileScreenViewModel

class ViewModelFactory(private val repository: UserRepository) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddScreenViewModel::class.java)) {
            return AddScreenViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(LoginScreenViewModel::class.java)) {
            return LoginScreenViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(HomeScreenViewModel::class.java)) {
            return HomeScreenViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(ProfileScreenViewModel::class.java)) {
            return ProfileScreenViewModel(repository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}