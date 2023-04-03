package ru.otus.daggerhomework

import android.content.Context
import android.graphics.Color
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class ViewModelProducer @Inject constructor(
    private val colorGenerator: ColorGenerator,

    private val context: Context
) : ViewModel() {
    private var _colorState = MutableStateFlow(Color.WHITE)
    val colorState: StateFlow<Int> = _colorState
    init{
        DaggerViewModelProducerComponent.factory().create(context)
    }

    fun generateColor()  {
//        if (context !is MainActivity ) throw RuntimeException("Здесь нужен контекст активити")
        _colorState.value = colorGenerator.generateColor()
        println("color ${colorState.value}")
        Toast.makeText(context, "Color sent", Toast.LENGTH_LONG).show()
    }
}

@Component(modules = [ColorGeneratorModule::class])
interface ViewModelProducerComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): ViewModelProducerComponent
    }
   fun inject(fragmentProducer:FragmentProducer)
}

@Module
interface ColorGeneratorModule {
    @Binds
    fun bindColorGenerator(colorGenerator: ColorGeneratorImpl): ColorGenerator
}

class ViewModelProducerFactory(private val colorGenerator: ColorGenerator,
                               private val context: Context) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        ViewModelProducer( colorGenerator, context ) as T
}
