package ru.otus.daggerhomework

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import androidx.annotation.ColorInt
import androidx.fragment.app.Fragment
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class FragmentReceiver: Fragment() {

    @Inject
    lateinit var viewModelReceiver: ViewModelReceiverImpl
//    @Inject
//    lateinit var colorGenerator: ColorGenerator
//    @Inject
//    lateinit var viewModelProducer: ViewModelProducer


    private lateinit var frameLayout: FrameLayout

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

           DaggerViewModelReceiverComponent
            .factory()
            .create(this.requireActivity().application)
            .inject(this)
        return inflater.inflate(R.layout.fragment_b, container, true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        frameLayout = view.findViewById(R.id.frame)
        frameLayout.setBackgroundColor(viewModelReceiver.newColor)
        viewModelReceiver.observeColors()

//        viewLifecycleOwner.lifecycleScope.launch {
//            repeatOnLifecycle(Lifecycle.State.STARTED){
//                populateColor(viewModelProducer.colorState.value)
//            }
//    }

    }

    private fun populateColor(@ColorInt color: Int) {
        frameLayout.setBackgroundColor(color)
    }
}
