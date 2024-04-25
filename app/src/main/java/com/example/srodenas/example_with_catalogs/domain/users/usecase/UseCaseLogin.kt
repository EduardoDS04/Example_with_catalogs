package com.example.srodenas.example_with_catalogs.domain.users.usecase

import com.example.srodenas.example_with_catalogs.domain.users.models.Repository
import com.example.srodenas.example_with_catalogs.domain.users.models.User

class UseCaseLogin (private val repository: Repository){


    suspend fun login(email: String, password: String): User?{
        return repository.isLoginEntity(email, password)
    }

}
