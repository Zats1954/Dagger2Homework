package ru.otus.daggerhomework

import android.app.Application
import android.content.Context
import android.graphics.Color

import android.widget.Toast
import androidx.annotation.ColorInt
import androidx.core.graphics.toColorInt
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import dagger.*
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

class ViewModelReceiverImpl @Inject constructor(
    private val context: Context,
//    private val colorGenerator: ColorGenerator
) : ViewModel ()   {
    var newColor:Int
    init{
        newColor = Color.parseColor("#228833")
        System.out.println("контекст ${context is Application} ")

    }

    fun observeColors() {

        if (context !is Application) throw RuntimeException("Здесь нужен контекст апликейшена")
        viewModelScope.launch {

        }
        Toast.makeText(context, "Color received", Toast.LENGTH_LONG).show()
    }
}

class ViewModelReceiverFactory @Inject constructor(
    private val context: Context,
//    private val colorGenerator: ColorGenerator
): ViewModelProvider.Factory{
    override fun <T: ViewModel> create(modelClass: Class<T>): T{
        return ViewModelReceiverImpl(context) as T
    }
}

@Component(
    modules = [ViewModelReceiverModule::class,ColorGeneratorModule::class]
    )
interface ViewModelReceiverComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance @ApplicationContext context: Context,
//                   @BindsInstance  colorGenerator: ColorGenerator
        ): ViewModelReceiverComponent
    }
    fun inject(fragmentReceiver:FragmentReceiver)
}

@Module
interface ViewModelReceiverModule{
    @Binds
    fun bindFactory(viewModelReceiverFactory: ViewModelReceiverFactory):ViewModelProvider.Factory
}
