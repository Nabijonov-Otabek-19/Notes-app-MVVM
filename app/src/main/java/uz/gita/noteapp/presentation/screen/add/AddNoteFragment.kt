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
import jp.wasabeef.richeditor.RichEditor
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
    private lateinit var editor: RichEditor

    private var updateData = false
    private var updateNoteId: Long = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        editor = binding.richEditor
        editor.setPlaceholder("Insert note here...")
        editor.setPadding(0, 8, 0, 8)

        clickEvents()

        if (args.noteData != null) {
            updateData = true
            binding.apply {
                edtTitle.setText(args.noteData!!.title)
                richEditor.html = args.noteData!!.content
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
                menu.clear()
                menuInflater.inflate(R.menu.add_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.add_note -> {
                        val title = binding.edtTitle.text.toString().trim()
                        val content = binding.richEditor.html.toString().trim()
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
                            } else {
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
                                    richEditor.html = ""
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

    private fun clickEvents() {
        binding.apply {
            actionBold.setOnClickListener {
                editor.setBold()
            }

            actionItalic.setOnClickListener {
                editor.setItalic()
            }

            actionSubscript.setOnClickListener {
                editor.setSubscript()
            }

            actionSuperscript.setOnClickListener {
                editor.setSuperscript()
            }

            actionHeading1.setOnClickListener {
                editor.setHeading(1)
            }

            actionHeading2.setOnClickListener {
                editor.setHeading(2)
            }

            actionHeading3.setOnClickListener {
                editor.setHeading(3)
            }

            actionHeading4.setOnClickListener {
                editor.setHeading(4)
            }

            actionHeading5.setOnClickListener {
                editor.setHeading(5)
            }

            actionHeading6.setOnClickListener {
                editor.setHeading(6)
            }

            actionTxtColor.setOnClickListener {
                Toast.makeText(requireContext(), "Clicked", Toast.LENGTH_SHORT).show()
            }

            actionBgColor.setOnClickListener {
                Toast.makeText(requireContext(), "Clicked", Toast.LENGTH_SHORT).show()
            }

            actionIndent.setOnClickListener {
                editor.setIndent()
            }

            actionOutdent.setOnClickListener {
                editor.setOutdent()
            }

            actionUnderline.setOnClickListener {
                editor.setUnderline()
            }

            actionStrikethrough.setOnClickListener {
                editor.setStrikeThrough()
            }

            actionAlignLeft.setOnClickListener {
                editor.setAlignLeft()
            }

            actionAlignCenter.setOnClickListener {
                editor.setAlignCenter()
            }

            actionAlignRight.setOnClickListener {
                editor.setAlignRight()
            }

            actionBlockquote.setOnClickListener {
                editor.setBullets()
            }

            actionInsertNumbers.setOnClickListener {
                editor.setNumbers()
            }

            actionInsertImage.setOnClickListener {
                Toast.makeText(requireContext(), "Clicked", Toast.LENGTH_SHORT).show()
            }

            actionInsertLink.setOnClickListener {
                Toast.makeText(requireContext(), "Clicked", Toast.LENGTH_SHORT).show()
            }

            actionInsertCheckbox.setOnClickListener {
                editor.insertTodo()
            }
        }
    }
}