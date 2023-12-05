package ru.otus.daggerhomework

import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

class ViewModelReceiverImpl @Inject constructor(
    private val context: Context,
    private val colorGenerator: ColorGenerator
) : ViewModel() {
    var showToast = true

    suspend fun observeColors(): StateFlow<Int> {
        if (context !is Application) throw RuntimeException("Здесь нужен контекст апликейшена")
        if (showToast) {
            Toast.makeText(context, "Color received", Toast.LENGTH_SHORT).show()
            showToast = false
        }
        return colorGenerator.getColor().stateIn(CoroutineScope(Dispatchers.IO))
    }
}

class ViewModelReceiverFactory @Inject constructor(
    private val context: Context,
    private val colorGenerator: ColorGenerator
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ViewModelReceiverImpl(context, colorGenerator) as T
    }
}


