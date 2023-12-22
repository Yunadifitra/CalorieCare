package com.example.caloriecar.data.response

import com.google.gson.annotations.SerializedName

data class AuthResponse(

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("token")
	val token: String
)
