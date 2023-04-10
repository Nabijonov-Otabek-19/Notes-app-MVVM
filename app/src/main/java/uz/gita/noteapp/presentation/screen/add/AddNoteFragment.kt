package uz.gita.noteapp.presentation.screen.add

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.noteapp.R
import uz.gita.noteapp.data.model.NoteData
import uz.gita.noteapp.data.source.local.converter.DateConverter
import uz.gita.noteapp.databinding.FragmentAddNoteBinding
import uz.gita.noteapp.presentation.screen.add.viewmodel.AddNoteViewModel
import uz.gita.noteapp.presentation.screen.add.viewmodel.impl.AddNoteViewModelImpl

class AddNoteFragment : Fragment(R.layout.fragment_add_note) {

    private val viewModel: AddNoteViewModel by viewModels<AddNoteViewModelImpl>()
    private val binding by viewBinding(FragmentAddNoteBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.apply {
            btnAdd.setOnClickListener {
                val title = edtTitle.text.toString().trim()
                val content = edtContent.text.toString().trim()
                val time = DateConverter.getCurrentTime()

                viewModel.addNote(NoteData(title = title, content = content, createdAt = time))

                Toast.makeText(requireActivity(), "Note added", Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = AddNoteFragment()
    }
}