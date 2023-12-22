package com.example.caloriecar.data

import com.example.caloriecar.data.model.User
import com.example.caloriecar.data.pref.UserPreference
import com.example.caloriecar.data.retrofit.ApiService
import kotlinx.coroutines.flow.Flow

class UserRepository(private val apiService: ApiService, private val userPreference: UserPreference) {

    suspend fun saveSession(user: User) {
        userPreference.saveSession(user)
    }

    fun getSession(): Flow<User> {
        return userPreference.getSession()
    }

    suspend fun logout() {
        userPreference.logout()
    }

    companion object {
        @Volatile
        private var instance: UserRepository? = null

        fun getInstance(
            apiService: ApiService,
            userPreference: UserPreference
        ): UserRepository =
            instance ?: synchronized(this) {
                instance ?: UserRepository(apiService, userPreference)
            }.also { instance = it }
    }
}