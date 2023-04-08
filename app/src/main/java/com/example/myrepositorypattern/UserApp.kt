package com.example.myrepositorypattern

import android.app.Application
import com.example.myrepositorypattern.local.UserDatabase
import com.example.myrepositorypattern.remote.ApiInterface
import retrofit2.Retrofit

class UserApp : Application() {
    companion object {
        // Retrofit
        lateinit var retrofit: Retrofit

        // Room
        lateinit var userDatabase: UserDatabase
    }

    override fun onCreate() {
        super.onCreate()

        // Retrofit
        retrofit = ApiInterface.RetrofitClient.getInstance()

        // Room
        userDatabase = UserDatabase.getInstance(applicationContext)
    }
}