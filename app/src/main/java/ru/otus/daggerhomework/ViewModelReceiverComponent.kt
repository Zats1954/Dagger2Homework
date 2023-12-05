package ru.otus.daggerhomework

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.BindsInstance
import dagger.Component
import dagger.Module

@Component(
    modules = [ViewModelReceiverModule::class, ColorGeneratorModule::class]
)
interface ViewModelReceiverComponent {

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance context: Context,
        ): ViewModelReceiverComponent
    }

    fun inject(fragmentReceiver: FragmentReceiver)
}

@Module
interface ViewModelReceiverModule {
    @Binds
    fun bindFactory(viewModelReceiverFactory: ViewModelReceiverFactory): ViewModelProvider.Factory
}