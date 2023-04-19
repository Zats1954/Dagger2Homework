package ru.otus.daggerhomework

import android.app.Application
import android.content.Context
import android.graphics.Color
import android.widget.Toast
import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class ViewModelReceiverImpl @Inject constructor(
    private val context: Context,
//    private val colorState: MutableStateFlow(null)
) : ViewModel() {

    lateinit var viewModelProducer:ViewModelProducer
    lateinit var newColor: Color
init{
    val clr = DaggerViewModelProducerComponent.factory().create(context).sendColor()
}
//    private val viewModelProducer = ViewModelProducer()

    fun observeColors() {
        if (context !is Application) throw RuntimeException("Здесь нужен контекст апликейшена")
        Toast.makeText(context, "Color received", Toast.LENGTH_LONG).show()
    }
}

@Component(modules = [ApplicationComponent::class] )
interface ViewModelReceiverComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context,
                  ): ViewModelReceiverComponent
    }
    fun inject(fragmentReceiver:FragmentReceiver)
}

//@Module
//interface ViewModelReceiver{
//    @Binds
//    fun bindViewModelReceiver() : ViewModelReceiver{
//        return ViewModelReceiverImpl( )
//    }

//}
