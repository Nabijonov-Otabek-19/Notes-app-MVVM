package uz.gita.noteapp.presentation.screen.archive

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.noteapp.R
import uz.gita.noteapp.databinding.FragmentArchiveBinding
import uz.gita.noteapp.databinding.FragmentHomeBinding
import uz.gita.noteapp.presentation.screen.home.viewmodel.HomeViewModel
import uz.gita.noteapp.presentation.screen.home.viewmodel.impl.HomeViewModelImpl

class ArchiveFragment : Fragment(R.layout.fragment_archive) {

    private val viewModel: HomeViewModel by viewModels<HomeViewModelImpl>()
    private val binding by viewBinding(FragmentArchiveBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {

        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = ArchiveFragment()
    }
}