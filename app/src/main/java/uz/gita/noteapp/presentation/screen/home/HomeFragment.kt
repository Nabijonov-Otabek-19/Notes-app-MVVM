package uz.gita.noteapp.presentation.screen.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.noteapp.R
import uz.gita.noteapp.databinding.FragmentHomeBinding
import uz.gita.noteapp.presentation.adapter.HomeAdapter
import uz.gita.noteapp.presentation.screen.home.viewmodel.HomeViewModel
import uz.gita.noteapp.presentation.screen.home.viewmodel.impl.HomeViewModelImpl

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val viewModel: HomeViewModel by viewModels<HomeViewModelImpl>()
    private val binding by viewBinding(FragmentHomeBinding::bind)
    private val adapter by lazy { HomeAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.openAddNoteScreenLiveData.observe(this, openAddNoteObserver)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter.setOnDeleteLongClickListener {
            viewModel.showDeleteDialog(requireActivity(), it.id)
        }

        binding.apply {
            addNoteBtn.setOnClickListener {
                viewModel.openAddNoteScreen()
            }
            recyclerHome.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        }

        viewModel.notesLiveData.observe(viewLifecycleOwner) {
            adapter.submitList(it)
            binding.recyclerHome.adapter = adapter
        }
    }

    private val openAddNoteObserver = Observer<Unit> {
        findNavController().navigate(R.id.action_homeFragment_to_addNoteFragment)
    }

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }
}