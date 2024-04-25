package com.example.srodenas.example_with_catalogs.data.users.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.srodenas.example_with_catalogs.data.users.database.dao.UserDao
import com.example.srodenas.example_with_catalogs.data.users.database.entities.UserEntity

@Database(entities = [UserEntity::class], version = 1)
abstract class UserDataBase : RoomDatabase(){
    abstract fun userDao(): UserDao
}