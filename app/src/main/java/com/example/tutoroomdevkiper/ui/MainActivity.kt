package com.example.tutoroomdevkiper.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tutoroomdevkiper.adapter.UserAdapter
import com.example.tutoroomdevkiper.data.UserApp
import com.example.tutoroomdevkiper.data.UserRepository
import com.example.tutoroomdevkiper.databinding.ActivityMainBinding
import com.example.tutoroomdevkiper.domain.User
import com.example.tutoroomdevkiper.utilities.Utilities
import com.example.tutoroomdevkiper.utilities.toast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    //ViewBinding
    private lateinit var binding: ActivityMainBinding

    //Singleton
    private val app by lazy { applicationContext as UserApp }

    //Repository
    private lateinit var repository: UserRepository

    //RecyclerView
    private lateinit var adapter: UserAdapter
    private val llmanager = LinearLayoutManager(this)

    private var users = emptyList<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initDB()
        initUI()
    }

    private fun initDB() {
        repository = UserRepository(app.db.userDAO())
    }

    private fun initUI() {
        binding.btnSave.setOnClickListener {
            addUser()
        }

        binding.btnDelete.setOnClickListener {
            deleteAllUsers()
        }

        adapter = UserAdapter(users)
        binding.rvUsers.adapter = adapter
        binding.rvUsers.layoutManager = llmanager
    }

    private fun addUser() {
        Utilities.hideKeyboard(binding.root)
        val name = binding.etName.text.toString()
        val surname = binding.etSurname.text.toString()

        if (name.trim().isNotEmpty() && surname.trim().isNotEmpty()) {
            lifecycleScope.launch(Dispatchers.IO) {
                //Add user
                repository.addUser(User(name = name, surname = surname))

                runOnUiThread {
                    getAllUsers()
                    binding.etName.text.clear()
                    binding.etSurname.text.clear()
                }
            }
        } else {
            toast("You must complete both fields")
        }

    }

    private fun getAllUsers() {
        lifecycleScope.launch(Dispatchers.IO) {
            users = repository.getUsers()

            runOnUiThread {
                updateAdapter()
            }
        }
    }

    private fun deleteAllUsers() {
        lifecycleScope.launch(Dispatchers.IO) {
            repository.deleteAllUsers()
            users = emptyList()

            runOnUiThread {
                updateAdapter()
            }
        }
    }

    private fun updateAdapter() {
        adapter.users = users
        adapter.notifyDataSetChanged()
    }

    override fun onStart() {
        getAllUsers()
        super.onStart()
    }

}

