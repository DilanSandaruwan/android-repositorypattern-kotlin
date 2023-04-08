package com.example.myrepositorypattern

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.myrepositorypattern.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    /**---After Creating ViewModelFactory START---**/
    private val viewModel: UserViewModel by lazy {
        val viewModelProviderFactory = UserViewModelProviderFactory()
        ViewModelProvider(this, viewModelProviderFactory)[UserViewModel::class.java]
    }

    /**---After Creating ViewModelFactory FINISH---**/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.userVM = viewModel
        binding.lifecycleOwner = this

        binding.button.setOnClickListener {
            viewModel.getUsers()
        }
    }
}