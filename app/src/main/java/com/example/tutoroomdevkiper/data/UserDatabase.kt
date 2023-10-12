package com.example.tutoroomdevkiper.data

import androidx.room.Database
import androidx.room.RoomDatabase

//We don't have to instantiate the database, that's in charge of Room

@Database(entities = [UserEntity::class], version = 1)
abstract class UserDatabase : RoomDatabase() {
    abstract val dao: UserDAO

}