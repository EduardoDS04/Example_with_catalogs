package com.example.srodenas.example_with_catalogs.domain.users.models

import com.example.srodenas.example_with_catalogs.data.users.database.dao.UserDao
import com.example.srodenas.example_with_catalogs.data.users.database.entities.UserEntity

class Repository private constructor(private val userDao: UserDao) {
    companion object {
        val repo: Repository by lazy {
            Repository(UserDataBaseSingleton.userDao)  // Utilizamos el singleton para acceder al userDao.
        }
    }

    suspend fun isLoginEntity(email: String, password: String): User? {
        val posUser: UserEntity? = userDao.login(email, password)
        return posUser?.let {
            User(
                id = it.id,
                name = it.name,
                email = it.email,
                passw = it.password,
                phone = it.phone,
                imagen = it.imagen
            )
        }
    }

    suspend fun registerEntity(user: User): Boolean {
        val exitUser = isLoginEntity(user.email, user.passw)
        return if (exitUser == null) {
            val userEntity = UserEntity(
                id = user.id,
                name = user.name,
                email = user.email,
                password = user.passw,
                phone = user.phone,
                imagen = user.imagen
            )
            userDao.insertUser(userEntity)
            true
        } else {
            false
        }
    }
}
