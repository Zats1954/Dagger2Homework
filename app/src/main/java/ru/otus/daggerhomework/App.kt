package ru.otus.daggerhomework

import android.app.Application
import javax.inject.Inject


class App :Application() {
private lateinit var appComponent: ApplicationComponent

override fun onCreate(){
    super.onCreate()
    appComponent = DaggerApplicationComponent.factory().create(this)
}
}