package ru.otus.daggerhomework

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.BindsInstance
import dagger.Component
import dagger.Module


@Component(
    modules = [ViewProducerModule::class, ColorGeneratorModule::class],
)
interface ViewModelProducerComponent {
    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance context: Context,
        ): ViewModelProducerComponent
    }

    fun inject(fragmentProducer: FragmentProducer)
}

@Module
interface ColorGeneratorModule {
    @Binds
    fun bindColorGenerator(colorGenerator: ColorGeneratorImpl): ColorGenerator
}

@Module
interface ViewProducerModule {
    @Binds
    fun bindFactory(viewModelProducerFactory: ViewModelProducerFactory): ViewModelProvider.Factory
}