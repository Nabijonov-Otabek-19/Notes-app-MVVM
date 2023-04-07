package uz.gita.noteapp.presentation.screen.trash

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.noteapp.R
import uz.gita.noteapp.databinding.FragmentTrashBinding
import uz.gita.noteapp.presentation.screen.trash.viewmodel.TrashViewModel
import uz.gita.noteapp.presentation.screen.trash.viewmodel.impl.TrashViewModelImpl

class TrashFragment : Fragment(R.layout.fragment_trash) {

    private val viewModel: TrashViewModel by viewModels<TrashViewModelImpl>()
    private val binding by viewBinding(FragmentTrashBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.apply {

        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = TrashFragment()
    }
}