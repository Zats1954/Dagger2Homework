package ru.otus.daggerhomework

import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.*
import javax.inject.Inject


class ViewModelProducerImpl @Inject constructor(
    private val context: Context,
    private val colorGenerator: ColorGenerator
) : ViewModel() {

    fun changeColor() {
        if (context !is MainActivity) throw RuntimeException("Здесь нужен контекст активити")
        colorGenerator.generateColor()
        Toast.makeText(context, "Color sent", Toast.LENGTH_SHORT).show()
    }
}

class ViewModelProducerFactory @Inject constructor(
    private val context: Context,
    private val colorGenerator: ColorGenerator
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ViewModelProducerImpl( context, colorGenerator  ) as T
    }
}

