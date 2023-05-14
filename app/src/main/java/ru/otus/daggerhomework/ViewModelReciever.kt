package ru.otus.daggerhomework

import android.app.Application
import android.content.Context
import android.graphics.Color
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import dagger.*
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class ViewModelReceiverImpl @Inject constructor(
    private val context: Context,
    private val colorGenerator: ColorGenerator
) : ViewModel ()   {
    var newColor : MutableStateFlow<Int>
    init{
        newColor = MutableStateFlow<Int>(Color.parseColor("#228833"))
        System.out.println("контекст Application ${context is Application} ")
    }

    suspend fun  observeColors():StateFlow<Int> {
        if (context !is Application) throw RuntimeException("Здесь нужен контекст апликейшена")
        colorGenerator.getColor().collect {
        System.out.println("ViewModelReceiver observeColors ${it} ")}
        Toast.makeText(context, "Color received", Toast.LENGTH_LONG).show()
            return  colorGenerator.getColor().stateIn(viewModelScope)
    }
}

class ViewModelReceiverFactory @Inject constructor(
    private val context: Context,
    private val colorGenerator: ColorGenerator
): ViewModelProvider.Factory{
    override fun <T: ViewModel> create(modelClass: Class<T>): T{
        return ViewModelReceiverImpl(context, colorGenerator ) as T
    }
}


@Component(
    modules = [ViewModelReceiverModule::class,ColorGeneratorModule::class]
    )
interface ViewModelReceiverComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance @ApplicationContext context: Context,
        ): ViewModelReceiverComponent
    }
    fun inject(fragmentReceiver:FragmentReceiver)
}

@Module
interface ViewModelReceiverModule{
    @Binds
    fun bindFactory(viewModelReceiverFactory: ViewModelReceiverFactory):ViewModelProvider.Factory
}
