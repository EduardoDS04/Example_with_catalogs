package com.example.srodenas.example_with_catalogs.ui.views.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.srodenas.example_with_catalogs.databinding.ActivityLoginBinding
import com.example.srodenas.example_with_catalogs.domain.users.models.User
import com.example.srodenas.example_with_catalogs.domain.users.models.UserDataBaseSingleton
import com.example.srodenas.example_with_catalogs.ui.viewmodel.users.UserViewModel
import com.example.srodenas.example_with_catalogs.ui.views.activities.fragments.dialogs.DialogRegisterUser

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val userViewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        UserDataBaseSingleton.init(applicationContext)
        registerLiveData()
        initEvents()
    }

    private fun initEvents() {
        binding.btnLogin.setOnClickListener {
            login()
        }
        binding.btnRegistro.setOnClickListener {
            showRegisterDialog()
        }
    }

    // Esta es la función que se llamará cuando se haga clic en el botón de login
    fun iniciarLogin(view: View) {
        login()
    }

    private fun login() {
        val email = binding.txtEmail.text.toString()
        val password = binding.txtPassword.text.toString()
        if (email.isNotEmpty() && password.isNotEmpty()) {
            userViewModel.isLogin(email, password)
        } else {
            Toast.makeText(this, "Email y contraseña no pueden estar vacíos", Toast.LENGTH_LONG).show()
        }
    }

    private fun showRegisterDialog() {
        val dialog = DialogRegisterUser { user ->
            okOnRegisterUser(user)
        }
        dialog.show(supportFragmentManager, "Registro de nuevo usuario")
    }

    private fun okOnRegisterUser(user: User) {
        userViewModel.register(user)
    }

    private fun registerLiveData() {
        userViewModel.login.observe(this, { login ->
            if (login != null) {
                startMainActivity()
            } else {
                Toast.makeText(this, "Error en el logueo", Toast.LENGTH_LONG).show()
            }
        })

        userViewModel.register.observe(this, { register ->
            if (register)
                Toast.makeText(this, "Usuario registrado correctamente", Toast.LENGTH_LONG).show()
            else
                Toast.makeText(this, "Usuario No registrado", Toast.LENGTH_LONG).show()

        })
    }

    private fun startMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
