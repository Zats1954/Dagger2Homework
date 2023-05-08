package ru.otus.daggerhomework

import android.graphics.Color
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.*
import javax.inject.Inject

interface ColorGenerator  {

    fun generateColor()
    @ColorInt
    fun getColor(): Int
}

class  ColorGeneratorImpl @Inject constructor(): ColorGenerator  {
    private var _colorState = MutableStateFlow(Color.BLUE)
    val colorState:StateFlow<Int>  = _colorState

     override fun generateColor() {
        val rnd = Random()
        _colorState.value = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
    }

    override fun getColor() = colorState.value
}
