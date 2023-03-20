package ru.otus.daggerhomework

import android.graphics.Color
import androidx.annotation.ColorInt
import dagger.Component
import java.util.*
import javax.inject.Inject

class  ColorGenerator  @Inject constructor() {

    fun generateColor(): Int {
        val rnd = Random()
        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
    }
}

@Component
interface  ColorGeneratorComponent  {
    @ColorInt
    fun generateColor(): Int
    fun inject (viewModelProducer: ViewModelProducer)
}

