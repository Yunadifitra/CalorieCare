package com.example.caloriecar.data.retrofit

import com.example.caloriecar.data.request.LoginRequest
import com.example.caloriecar.data.response.AuthResponse
import com.example.caloriecar.data.response.LoginResponse
import com.google.gson.JsonObject
import retrofit2.http.*
import retrofit2.Response

interface ApiService {

    @POST("Register")
    suspend fun register(
        @Body body: JsonObject
    ): Response<JsonObject>

//    @POST("Login")
//    suspend fun login(
//        @Body body: LoginRequest
//    ): Response<AuthResponse>

    @FormUrlEncoded
    @POST("login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Response<LoginResponse>

    //    @GET("stories")
//    suspend fun getAllStories(
//        @Header("Authorization") token: String,
//        @Query("page") page: Int = 1,
//        @Query("size") size: Int = 20
//    ): Response<StoriesResponse>
//
//    @Multipart
//    @POST("stories")
//    fun createStory(
//        @Header("Authorization") token: String,
//        @Part file: MultipartBody.Part,
//        @Part("description") description: RequestBody,
//    ): Call<RegisterResponse>
//
//    @GET("stories/{id}")
//    suspend fun getDetailStory(
//        @Header("Authorization") token: String,
//        @Path("id") id: String
//    ): Response<DetailStoryResponse>

    companion object {
        const val BASE_URL = "https://nice-symbol-403014.et.r.appspot.com/"
    }
}