package com.example.tutoroomdevkiper.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    @ColumnInfo(name = "first_name")
    val name: String,
    @ColumnInfo(name = "last_name")
    val surname: String
)
