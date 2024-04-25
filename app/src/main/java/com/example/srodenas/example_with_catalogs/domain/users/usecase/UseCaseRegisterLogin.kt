package com.example.srodenas.example_with_catalogs.domain.users.usecase

import com.example.srodenas.example_with_catalogs.domain.users.models.Repository
import com.example.srodenas.example_with_catalogs.domain.users.models.User

class UseCaseRegisterLogin (private val repository: Repository){

    suspend fun register(user: User): Boolean {
        return repository.registerEntity(user)
    }
}