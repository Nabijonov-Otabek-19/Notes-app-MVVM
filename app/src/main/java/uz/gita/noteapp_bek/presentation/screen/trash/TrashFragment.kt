package uz.gita.noteapp_bek.presentation.screen.trash

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.noteapp_bek.R
import uz.gita.noteapp_bek.databinding.FragmentTrashBinding
import uz.gita.noteapp_bek.presentation.adapter.TrashAdapter
import uz.gita.noteapp_bek.presentation.screen.trash.viewmodel.TrashViewModel
import uz.gita.noteapp_bek.presentation.screen.trash.viewmodel.impl.TrashViewModelImpl

class TrashFragment : Fragment(R.layout.fragment_trash) {

    private val viewModel: TrashViewModel by viewModels<TrashViewModelImpl>()
    private val binding by viewBinding(FragmentTrashBinding::bind)
    private val adapter by lazy { TrashAdapter() }
    private var listCount = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        requireActivity().addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menu.clear()
                menuInflater.inflate(R.menu.trash_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.clear_notes -> {
                        if (listCount != 0) viewModel.showDeleteAllDialog(requireActivity())
                        true
                    }
                    else -> {
                        false
                    }
                }
            }
        }, viewLifecycleOwner)

        adapter.setOnDeleteLongClickListener {
            viewModel.showRecoverDialog(requireActivity(), it.id)
        }

        binding.apply {
            recyclerTrash.layoutManager =
                StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        }

        viewModel.notesTrashLiveData.observe(viewLifecycleOwner) {
            listCount = it.size

            if (listCount == 0) binding.imgBin.visibility = View.VISIBLE
            else binding.imgBin.visibility = View.INVISIBLE

            adapter.submitList(it)
            binding.recyclerTrash.adapter = adapter
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = TrashFragment()
    }
}