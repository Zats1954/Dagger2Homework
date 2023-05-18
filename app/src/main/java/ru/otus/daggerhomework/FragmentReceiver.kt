package ru.otus.daggerhomework

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.annotation.ColorInt
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject


class FragmentReceiver : Fragment() {
    @Inject
    lateinit var viewModelReceiver: ViewModelReceiverImpl
    private lateinit var frameLayout: FrameLayout
    private var oldColor = 0
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
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                while (true) {
                    delay(50)
                    launch {
                        viewModelReceiver.observeColors().collect {
//   StateFlow doesn’t have an observe method (https://www.kodeco.com/22030171-reactive-streams-on-kotlin-sharedflow-and-stateflow)
                            if (oldColor != it) {
                                populateColor(it)
                                oldColor = it
                                viewModelReceiver.showToast = true
                            }
                        }
                    }
                }

            }
        }
    }

    private fun populateColor(@ColorInt color: Int) {
        frameLayout.setBackgroundColor(color)
    }
}
