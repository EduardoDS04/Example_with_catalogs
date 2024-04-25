package com.example.srodenas.example_with_catalogs.domain.users.models

import android.content.Context
import androidx.room.Room
import com.example.srodenas.example_with_catalogs.data.users.database.UserDataBase

object UserDataBaseSingleton {
    private var database: UserDataBase? = null
    val userDao by lazy {
        database?.userDao() ?: throw IllegalStateException("Database not initialized")
    }

    // Creo una instancia de Room
    fun init(context: Context){
        if (database == null) {
            synchronized(this) {
                if (database == null) {
                    database = Room.databaseBuilder(
                        context.applicationContext,
                        UserDataBase::class.java,
                        "my_app_user"
                    ).build()
                }
            }
        }
    }
}
