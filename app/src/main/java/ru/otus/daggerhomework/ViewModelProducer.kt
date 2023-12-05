package ru.otus.daggerhomework

import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.*
import javax.inject.Inject


class ViewModelProducerImpl @Inject constructor(
    private val colorGenerator: ColorGenerator,
    private val context: Context
) : ViewModel() {

    fun changeColor() {
        if (context !is MainActivity) throw RuntimeException("Здесь нужен контекст активити")
        colorGenerator.generateColor()
        Toast.makeText(context, "Color sent", Toast.LENGTH_SHORT).show()
    }
}

class ViewModelProducerFactory @Inject constructor(
    private val colorGenerator: ColorGenerator,
    private val context: Context
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ViewModelProducerImpl(colorGenerator, context ) as T
    }
}

@Component(
    modules = [ViewProducerModule::class,ColorGeneratorModule::class],
)
interface ViewModelProducerComponent {
    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance  context: Context,
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