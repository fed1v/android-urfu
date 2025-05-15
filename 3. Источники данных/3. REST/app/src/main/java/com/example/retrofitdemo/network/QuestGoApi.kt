package com.example.retrofitdemo.network

import okhttp3.MultipartBody
import retrofit2.http.Body
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import com.example.retrofitdemo.screens.profile.models.RetrofitCreateElementRequest

interface QuestGoApi {

    @POST("/createElement")
    suspend fun sendElementRequest(@Body elementModel: RetrofitCreateElementRequest)

    @POST("/sendPhoto")
    @Multipart
    suspend fun sendFile(@Part body: MultipartBody.Part)
}