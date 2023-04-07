package uz.gita.noteapp.presentation.screen.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.noteapp.R
import uz.gita.noteapp.databinding.FragmentHomeBinding
import uz.gita.noteapp.presentation.adapter.HomeAdapter
import uz.gita.noteapp.presentation.screen.home.viewmodel.HomeViewModel
import uz.gita.noteapp.presentation.screen.home.viewmodel.impl.HomeViewModelImpl

class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModels<HomeViewModelImpl>()
    private val binding by viewBinding(FragmentHomeBinding::bind)
    private val adapter by lazy { HomeAdapter() }

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

            recyclerHome.layoutManager = LinearLayoutManager(requireActivity())
        }

        viewModel.openAddNoteScreenLiveData.observe(viewLifecycleOwner) {}

        viewModel.notesLiveData.observe(viewLifecycleOwner) {
            adapter.submitList(it)
            binding.recyclerHome.adapter = adapter
        }
    }


    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }
}