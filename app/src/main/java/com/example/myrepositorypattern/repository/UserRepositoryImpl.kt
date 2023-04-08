package com.example.myrepositorypattern.repository

import android.util.Log
import com.example.myrepositorypattern.local.UserDao
import com.example.myrepositorypattern.model.User
import com.example.myrepositorypattern.remote.ApiInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepositoryImpl(
    private val userDao: UserDao,
    private val userService: ApiInterface,
) : UserRepository {

    override suspend fun getUsers(): List<User> {
        var users: List<User>
        return withContext(Dispatchers.IO) {
            users = getUsersFromRemoteService()
            if (users.isNotEmpty()) {
                addUsersToLocalDb(users)
            }
            return@withContext getUsersFromLocalDb()
        }
    }

    private suspend fun getUsersFromRemoteService(): List<User> {
        var users = listOf<User>()
        val response = userService.getUsers()
        if (response.isSuccessful) {
            val userResponse = response.body()
            users = userResponse!!.data
            Log.d("REPOSITORY", "Remote user data fetching SUCCESSFUL users size: ${users.size}")
        }
        return users
    }

    private suspend fun getUsersFromLocalDb(): List<User> {
        var users = userDao.getAllUsers()
        Log.d(
            "REPOSITORY",
            "getUsersFromLocalDb: Local user data fetching SUCCESSFUL users size: ${users.size}"
        )
        return users
    }

    private suspend fun addUsersToLocalDb(users: List<User>) {
        val rowIds = mutableListOf<Long>()
        for (user in users) {
            // do not send user.id to DB -> to avoid UNIQUE constraint failure
            val userWithoutId =
                User(
                    firstName = user.firstName,
                    lastName = user.lastName,
                    email = user.email,
                    avatar = user.avatar
                )
            rowIds.add(userDao.insertUser(userWithoutId))
        }
        Log.d("REPOSITORY", "addUsersToLocalDb: Local uer data saving SUCCESSFUL users row Ids: $rowIds")
    }
}