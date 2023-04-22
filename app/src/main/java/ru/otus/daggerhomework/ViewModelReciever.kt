package ru.otus.daggerhomework

import android.app.Application
import android.content.Context
import android.graphics.Color
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import dagger.Binds
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

class ViewModelReceiverImpl @Inject constructor(
    @Named("ApplicationContext")private val context: Context
//    private val colorState: MutableStateFlow(null)
) : ViewModel ()   {

    lateinit var viewModelProducer:ViewModelProducer
    lateinit var newColor: Color

    fun observeColors() {
        if (context !is Application) throw RuntimeException("Здесь нужен контекст апликейшена")
        Toast.makeText(context, "Color received", Toast.LENGTH_LONG).show()
    }
}

class ViewModelReceiverFactory @Inject constructor(
    private val context: Context
): ViewModelProvider.Factory{
    override fun <T: ViewModel> create(modelClass: Class<T>): T{
        return ViewModelReceiverImpl(context) as T
    }
}


@Component(dependencies = [ApplicationComponent::class],
    modules = [ViewModelReceiverModule::class]
    )
interface ViewModelReceiver {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context ): ViewModelReceiver
    }
    fun inject(fragmentReceiver:FragmentReceiver)
}

@Module
interface ViewModelReceiverModule{
    @Binds
    fun bindViewModelReceiver(viewModelReceiver:ViewModelReceiver) : ViewModelReceiver

}
