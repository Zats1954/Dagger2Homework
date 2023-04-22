package ru.otus.daggerhomework

import android.content.Context
import dagger.*
import javax.inject.Qualifier


@Component(
    modules = [ApplicationModule::class]
)
interface ApplicationComponent {

//    @ApplicationContext
//    @Provides
//    fun provideContext(): Context

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance @ApplicationContext context: Context): ApplicationComponent
    }
}

annotation class ApplicationContext

@Module
interface ApplicationModule{
    @Binds
    fun bindContext(context: Context): Context{
        return context
    }
}