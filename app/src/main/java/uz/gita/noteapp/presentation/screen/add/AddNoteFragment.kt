package uz.gita.noteapp.presentation.screen.add

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
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

    private val args: AddNoteFragmentArgs by navArgs()

    private var updateData = false
    private var updateNoteId: Long = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        if (args.noteData != null) {
            updateData = true
            binding.apply {
                edtTitle.setText(args.noteData!!.title)
                edtContent.setText(args.noteData!!.content)
                updateData = args.type
            }
            updateNoteId = args.noteData!!.id
        }

        binding.apply {
            imgColor.setOnClickListener {
                viewModel.showSetColorDialog(requireActivity())
            }
        }

        requireActivity().addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.add_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.add_note -> {
                        val title = binding.edtTitle.text.toString().trim()
                        val content = binding.edtContent.text.toString().trim()
                        val time = DateConverter.getCurrentTime()

                        if (title.isNotEmpty() && content.isNotEmpty()) {
                            if (updateData) {
                                viewModel.updateNote(updateNoteId, title, content, time)
                                Toast.makeText(
                                    requireActivity(),
                                    "Note updated",
                                    Toast.LENGTH_SHORT
                                )
                                    .show()
                            } else if (!updateData) {
                                viewModel.addNote(
                                    NoteData(
                                        title = title, content = content,
                                        createdAt = time, color = viewModel.getColor(), pinned = 0
                                    )
                                )
                                Toast.makeText(requireActivity(), "Note added", Toast.LENGTH_SHORT)
                                    .show()
                                binding.apply {
                                    edtTitle.setText("")
                                    edtContent.setText("")
                                }
                            }
                        }
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner)
    }
}