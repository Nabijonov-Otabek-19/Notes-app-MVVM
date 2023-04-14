package uz.gita.noteapp.presentation.screen.archive

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.noteapp.R
import uz.gita.noteapp.databinding.FragmentArchiveBinding
import uz.gita.noteapp.presentation.adapter.ArchiveAdapter
import uz.gita.noteapp.presentation.screen.archive.viewmodel.ArchiveViewModel
import uz.gita.noteapp.presentation.screen.archive.viewmodel.impl.ArchiveViewModelImpl

class ArchiveFragment : Fragment(R.layout.fragment_archive) {

    private val viewModel: ArchiveViewModel by viewModels<ArchiveViewModelImpl>()
    private val binding by viewBinding(FragmentArchiveBinding::bind)
    private val adapter by lazy { ArchiveAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            recyclerArchive.layoutManager =
                StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        }

        adapter.setOnDeleteLongClickListener {
            viewModel.showBottomSheetDialog(requireActivity(), it.id)
        }

        viewModel.notesArchivedLiveData.observe(viewLifecycleOwner) {
            if (it.isEmpty()) binding.imgArchive.visibility = View.VISIBLE
            else binding.imgArchive.visibility = View.INVISIBLE

            adapter.submitList(it)
            binding.recyclerArchive.adapter = adapter
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = ArchiveFragment()
    }
}