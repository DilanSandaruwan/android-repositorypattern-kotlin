package com.example.myrepositorypattern

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myrepositorypattern.remote.ApiInterface
import com.example.myrepositorypattern.repository.UserRepositoryImpl

class UserViewModelProviderFactory() : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        val userDao = UserApp.userDatabase.userDao()
        val userService = UserApp.retrofit.create(ApiInterface::class.java)
        val userRepository = UserRepositoryImpl(userDao = userDao, userService = userService)

        val viewModel = UserViewModel(userRepository)

        return viewModel as T
    }
}