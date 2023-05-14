package ru.otus.daggerhomework

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import dagger.*
import javax.inject.Inject


class ViewModelProducer @Inject constructor(
    private val colorGenerator: ColorGenerator,
    private val context: Context
) : ViewModel() {
    fun changeColor() {
//        System.out.println("context is MainActivity ${context is MainActivity}")
        if (context !is MainActivity) throw RuntimeException("Здесь нужен контекст активити")
        colorGenerator.generateColor()
        Toast.makeText(context, "Color sent", Toast.LENGTH_LONG).show()
    }
}

@Component(
    modules = [ColorGeneratorModule::class],
//         dependencies = [MainActivityComponent::class]
)
interface ViewModelProducerComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): ViewModelProducerComponent
    }

    fun inject(fragmentProducer: FragmentProducer)
}

@Module
interface ColorGeneratorModule {
    @Binds
    fun bindColorGenerator(colorGenerator: ColorGeneratorImpl): ColorGenerator
}
