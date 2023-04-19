package ru.otus.daggerhomework

import android.content.Context
import android.graphics.Color
import android.widget.Toast
import androidx.annotation.ColorInt
import androidx.lifecycle.ViewModel
import dagger.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import java.util.stream.DoubleStream.builder
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Qualifier

class ViewModelProducer @Inject constructor(
    private val colorGenerator: ColorGenerator,
//    @Named("@ApplicationContext")
    private val context: Context
) : ViewModel() {
    private var _colorState = MutableStateFlow(Color.WHITE)

    val colorState: StateFlow<Int> = _colorState



    fun generateColor()  {
        System.out.println("context is MainActivity ${context is MainActivity}")
        if (context !is MainActivity ) throw RuntimeException("Здесь нужен контекст активити")
        _colorState.value = colorGenerator.generateColor()
        println("color ${colorState.value}")

        Toast.makeText(context, "Color sent", Toast.LENGTH_LONG).show()
    }
}

@Component(modules = [ColorGeneratorModule::class])
interface ViewModelProducerComponent {
    companion object{
        fun getViewModelProducerComponent(applicationComponent: ApplicationComponent):ViewModelProducerComponent{
            return DaggerViewModelProducerComponent.
        }
    }
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

    @Binds
    fun bindFactory(viewModelProducer:ViewModelProducer): ViewModelProducer_Factory
}

//@Module
//interface ViewModelProducerModule{
//    @Provides
//    fun provideMainActivityContext():MainActivityContext
//}