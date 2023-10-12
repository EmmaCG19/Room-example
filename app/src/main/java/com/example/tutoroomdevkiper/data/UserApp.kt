package com.example.tutoroomdevkiper.data

import android.app.Application
import androidx.room.Room

//Initialize first the database when starting the app
class UserApp : Application() {

    lateinit var db: UserDatabase

    override fun onCreate() {
        super.onCreate()
        db = Room
            .databaseBuilder(applicationContext, UserDatabase::class.java, "user_db")
            .build()
    }


}