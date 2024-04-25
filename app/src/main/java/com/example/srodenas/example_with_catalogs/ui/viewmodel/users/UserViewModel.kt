package com.example.srodenas.example_with_catalogs.ui.viewmodel.users

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.srodenas.example_with_catalogs.domain.users.models.Repository
import com.example.srodenas.example_with_catalogs.domain.users.models.User
import com.example.srodenas.example_with_catalogs.domain.users.usecase.UseCaseLogin
import com.example.srodenas.example_with_catalogs.domain.users.usecase.UseCaseRegisterLogin
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserViewModel(): ViewModel() {
    val login = MutableLiveData<User?>()
    private val repository = Repository.repo  // Aquí tengo la instancia del repositorio
    private val useCaseLogin = UseCaseLogin(repository)
    private val useCaseRegisterLogin = UseCaseRegisterLogin(repository)
    val register = MutableLiveData<Boolean>()

    fun isLogin(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) { // Esta corrutina se ejecuta en el hilo de E/S, no en el principal.
            if (email.isNotEmpty() && password.isNotEmpty()) {
                val user = useCaseLogin.login(email, password) // Invocamos al usuario.
                withContext(Dispatchers.Main) { // Con Dispatchers.Main, indicamos que el hilo se ejecute en el principal.
                    login.postValue(user)
                }
            }
        }
    }

    fun register(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            val isReg = useCaseRegisterLogin.register(user) // Registramos el usuario
            withContext(Dispatchers.Main) {
                register.value = isReg
            }
        }
    }
}







