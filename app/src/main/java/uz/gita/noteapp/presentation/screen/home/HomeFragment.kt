package uz.gita.noteapp.presentation.screen.home

import android.os.Bundle
import android.view.View
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
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

        adapter.setOnItemClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToAddNoteFragment2(it)
            findNavController().navigate(action)
        }

        binding.apply {
            searchHome.doOnTextChanged { text, start, before, count ->
                if (text.toString().isNotBlank()) {

                    viewModel.searchNote("%${text.toString()}%").observe(viewLifecycleOwner) {
                        if (it.isNotEmpty()) binding.txtNotFound.visibility = View.INVISIBLE
                        else {
                            binding.txtNotFound.visibility = View.VISIBLE
                            binding.txtNotFound.text = "Not Found"
                        }

                        adapter.submitList(it)
                        recyclerHome.adapter = adapter
                    }
                } else {
                    viewModel.notesLiveData.observe(viewLifecycleOwner) {
                        if (it.isNotEmpty()) binding.txtNotFound.visibility = View.INVISIBLE
                        else {
                            binding.txtNotFound.visibility = View.VISIBLE
                            binding.txtNotFound.text = "There is not any Notes."
                        }

                        adapter.submitList(it)
                        binding.recyclerHome.adapter = adapter
                    }
                }
            }

            addNoteBtn.setOnClickListener {
                viewModel.openAddNoteScreen()
            }

            recyclerHome.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        }

        viewModel.notesLiveData.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) binding.txtNotFound.visibility = View.INVISIBLE
            else {
                binding.txtNotFound.visibility = View.VISIBLE
                binding.txtNotFound.text = "There is not any Notes."
            }

            adapter.submitList(it)
            binding.recyclerHome.adapter = adapter
        }
    }

    private val openAddNoteObserver = Observer<Unit> {
        findNavController().navigate(R.id.action_homeFragment_to_addNoteFragment2)
    }
}