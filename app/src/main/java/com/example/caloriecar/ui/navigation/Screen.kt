package com.example.caloriecar.ui.navigation

sealed class Screen(val route: String) {
    object Home: Screen("home")
    object Profile: Screen("profile")
    object Exercise: Screen("exercise")
    object Login: Screen("login")
    object Register: Screen("register")
    object Add: Screen("add")
}