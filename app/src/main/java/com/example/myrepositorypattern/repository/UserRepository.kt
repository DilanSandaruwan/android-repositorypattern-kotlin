package com.example.myrepositorypattern.repository

import com.example.myrepositorypattern.model.User

interface UserRepository {

    suspend fun getUsers(): List<User>

}