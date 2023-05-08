package ru.otus.daggerhomework

import android.content.Context
import dagger.*
import javax.inject.Qualifier


@Component(
//    modules = [ApplicationModule::class]
)
interface ApplicationComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance @ApplicationContext context: Context): ApplicationComponent
    }
}

annotation class ApplicationContext

//@Module
//object ApplicationModule{
//    @Provides
//    fun provideContext(context: Context): Context{
//        return context
//    }
//}