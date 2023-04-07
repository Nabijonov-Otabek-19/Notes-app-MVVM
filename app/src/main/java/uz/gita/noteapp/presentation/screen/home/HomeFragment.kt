package uz.gita.noteapp.presentation.screen.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.noteapp.R
import uz.gita.noteapp.databinding.FragmentHomeBinding
import uz.gita.noteapp.presentation.screen.home.viewmodel.HomeViewModel
import uz.gita.noteapp.presentation.screen.home.viewmodel.impl.HomeViewModelImpl

class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModels<HomeViewModelImpl>()
    private val binding by viewBinding(FragmentHomeBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            addNoteBtn.setOnClickListener {
                viewModel.openAddNoteScreen()
                findNavController().navigate(R.id.action_homeFragment_to_addNoteFragment)
            }
        }

        viewModel.openAddNoteScreenLiveData.observe(viewLifecycleOwner) {

        }
    }


    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }
}