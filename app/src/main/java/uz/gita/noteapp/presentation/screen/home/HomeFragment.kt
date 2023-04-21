package uz.gita.noteapp.presentation.screen.home

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuProvider
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
    private val adapter by lazy { HomeAdapter(requireActivity()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.openAddNoteScreenLiveData.observe(this, openAddNoteObserver)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter.setOnDeleteLongClickListener {
            viewModel.showBottomSheetDialog(requireActivity(), it.id, it.pinned)
        }

        adapter.setOnItemClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToAddNoteFragment(it, true)
            findNavController().navigate(action)
        }

        binding.apply {
            addNoteBtn.setOnClickListener {
                viewModel.openAddNoteScreen()
            }

            recyclerHome.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        }

        viewModel.notesLiveData.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) binding.imgNote.visibility = View.INVISIBLE
            else {
                binding.imgNote.visibility = View.VISIBLE
            }

            adapter.submitList(it)
            binding.recyclerHome.adapter = adapter
        }

        requireActivity().addMenuProvider(object : MenuProvider {

            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menu.clear()
                menuInflater.inflate(R.menu.home_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.home_search -> {

                        val searchItem = menuItem.actionView as SearchView
                        searchItem.queryHint = "Search Notes"

                        searchItem.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                            override fun onQueryTextSubmit(query: String?): Boolean {
                                return false
                            }

                            override fun onQueryTextChange(newText: String?): Boolean {
                                viewModel.searchNote("%${newText}%")
                                    .observe(viewLifecycleOwner) {
                                        if (it.isNotEmpty()) binding.imgNote.visibility =
                                            View.INVISIBLE
                                        else {
                                            binding.imgNote.visibility = View.VISIBLE
                                        }
                                        adapter.submitList(it)
                                        binding.recyclerHome.adapter = adapter
                                    }
                                return false
                            }
                        })
                        true
                    }
                    else -> false
                }
            }
        })
    }

    private val openAddNoteObserver = Observer<Unit> {
        val action = HomeFragmentDirections.actionHomeFragmentToAddNoteFragment(null, false)
        findNavController().navigate(action)
    }
}