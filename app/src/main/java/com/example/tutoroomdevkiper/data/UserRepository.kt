package com.example.tutoroomdevkiper.data

import com.example.tutoroomdevkiper.domain.User

class UserRepository(
    private val userDAO: UserDAO
) {

    suspend fun getUsers(): List<User> {
        val entities = userDAO.getUsers()
        return entities.map {
            User(
                name = it.name,
                surname = it.surname
            )
        }
    }

    suspend fun addUser(user: User) {
        val entity = UserEntity(name = user.name, surname = user.surname)
        userDAO.insertUser(entity)
    }

    suspend fun deleteAllUsers() {
        userDAO.deleteAllUsers()
    }

}