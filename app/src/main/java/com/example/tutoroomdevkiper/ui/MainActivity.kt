package com.example.tutoroomdevkiper.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.tutoroomdevkiper.data.UserApp
import com.example.tutoroomdevkiper.data.UserDatabase
import com.example.tutoroomdevkiper.data.UserRepository
import com.example.tutoroomdevkiper.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var repository: UserRepository
    private val app by lazy { applicationContext as UserApp }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        repository = UserRepository(app.db.userDAO())

        lifecycleScope.launch(Dispatchers.IO) {
            val users = repository.getUsers()
            Log.d("", "Table 'users' size = ${users.size ?: 0} registries")
        }
    }

}