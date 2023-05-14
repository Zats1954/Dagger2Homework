package ru.otus.daggerhomework

import android.graphics.Color
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import java.util.*
import javax.inject.Inject

interface ColorGenerator {
    fun generateColor(): Int
    suspend fun getColor(): Flow<Int>
}

class ColorGeneratorImpl @Inject constructor() : ColorGenerator {
//    private var _colorState = MutableStateFlow<Int>(Color.BLUE)

    var color: Int = -11332255
    private val rnd = Random()

    override fun generateColor(): Int {
//        _colorState.value = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))

        color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
        System.out.println("ColorGenerator generateColor $color  ")
        return color
    }

    override suspend fun getColor(): Flow<Int> {
//        val colorState = _colorState.asStateFlow()

//        return flowOf(Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256)))
        System.out.println("ColorGenerator colorState getColor $color")
        return flowOf(color)
    }
}
