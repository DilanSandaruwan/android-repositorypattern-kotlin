package com.example.myrepositorypattern

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myrepositorypattern.repository.UserRepository
import kotlinx.coroutines.launch

class UserViewModel(private val userRepo: UserRepository) : ViewModel() {

    private val _userData = MutableLiveData<String>(null)
    val userData: LiveData<String>
        get() = _userData

    fun getUsers() {
        viewModelScope.launch {

            val users = userRepo.getUsers()
            var strUser: String? = null
            var userRecord = ""

            for (user in users)
                strUser = "${user.id}\n${user.firstName}\n${user.lastName}\n${user.email}\n\n"
            userRecord += strUser

            _userData.value = userRecord
        }
    }

}