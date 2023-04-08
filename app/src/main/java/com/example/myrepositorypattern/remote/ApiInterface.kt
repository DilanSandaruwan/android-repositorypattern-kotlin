package com.example.myrepositorypattern.remote

import com.example.myrepositorypattern.model.UserResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiInterface {

    @GET("/api/users?page=2")
    suspend fun getUsers(): Response<UserResponse>

    object RetrofitClient {
        fun getInstance(): Retrofit {
            return Retrofit.Builder()
                .baseUrl("https://reqres.in")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        const val SERVICE_URL = "https://reqres.in/api/users?page=2"

    }
}