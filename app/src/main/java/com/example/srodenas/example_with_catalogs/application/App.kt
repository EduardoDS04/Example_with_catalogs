package com.example.srodenas.example_with_catalogs.application

import android.app.Application
import com.example.srodenas.example_with_catalogs.domain.users.models.UserDataBaseSingleton
class App : Application() {


    override fun onCreate() {
        super.onCreate()
        UserDataBaseSingleton.init(this)//tengo que pasarle el contexto, para que se cree.
    }


}