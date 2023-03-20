package ru.otus.daggerhomework

import android.content.Context
import android.graphics.Color
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import dagger.Component
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class ViewModelProducer  constructor(
    private val context: Context
): ViewModel() {

    private var _colorState = MutableStateFlow(Color.WHITE)
    val colorState: StateFlow<Int> = _colorState

     @Inject lateinit var colorGenerator:ColorGenerator

     init{
        DaggerColorGeneratorComponent.create().inject(this)
     }

    fun generateColor(): Int {
        var newColor = Color.WHITE
        if (context !is FragmentActivity) throw RuntimeException("Здесь нужен контекст активити")
        _colorState.value = colorGenerator.generateColor()
        Toast.makeText(context, "Color sent", Toast.LENGTH_LONG).show()
        return newColor
    }
}
//@Component
//interface ViewModelProducerComponent {
//   fun inject(fragmentProducer:FragmentProducer)
//}
