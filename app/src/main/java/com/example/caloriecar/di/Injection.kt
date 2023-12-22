package com.example.caloriecar.di

import android.content.Context
import com.example.caloriecar.data.UserRepository
import com.example.caloriecar.data.pref.UserPreference
import com.example.caloriecar.data.pref.dataStore
import com.example.caloriecar.data.retrofit.ApiConfig

object Injection {
    fun provideRepository(context: Context): UserRepository {
        val apiService = ApiConfig.getApiService()
        val pref = UserPreference.getInstance(context.dataStore)

        return UserRepository.getInstance(apiService, pref)
    }
}