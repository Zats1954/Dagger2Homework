package ru.otus.daggerhomework

import android.content.Context
import dagger.*

@Component()

interface ApplicationComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance  context: Context): ApplicationComponent
    }
}

