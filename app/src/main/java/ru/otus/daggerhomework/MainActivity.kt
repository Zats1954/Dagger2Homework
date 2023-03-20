package ru.otus.daggerhomework

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    @Inject
     lateinit var mainActivityComponent: MainActivityComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivityComponent = DaggerMainActivityComponent.factory().create(this)
        setContentView(R.layout.activity_main)
//        mainActivityComponent.inject(this)
    }
}

@Component()
interface MainActivityComponent {
//    fun inject (mainActivity: MainActivity)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): MainActivityComponent
    }
}