package uz.gita.noteapp_bek.presentation.screen.add

import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import jp.wasabeef.richeditor.RichEditor
import uz.gita.noteapp_bek.R
import uz.gita.noteapp_bek.data.model.NoteData
import uz.gita.noteapp_bek.data.source.local.converter.DateConverter
import uz.gita.noteapp_bek.databinding.FragmentAddNoteBinding
import uz.gita.noteapp_bek.presentation.screen.add.viewmodel.AddNoteViewModel
import uz.gita.noteapp_bek.presentation.screen.add.viewmodel.impl.AddNoteViewModelImpl

class AddNoteFragment : Fragment(R.layout.fragment_add_note) {

    private val viewModel: AddNoteViewModel by viewModels<AddNoteViewModelImpl>()
    private val binding by viewBinding(FragmentAddNoteBinding::bind)

    private val args: AddNoteFragmentArgs by navArgs()
    private lateinit var editor: RichEditor

    private var updateData = false
    private var updateNoteId: Long = 0

    @RequiresApi(Build.VERSION_CODES.S)
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
                        val content = binding.richEditor.html
                        val time = DateConverter.getCurrentTime()

                        if (title.isNotEmpty() && content != null) {
                            if (updateData) {
                                viewModel.updateNote(updateNoteId, title, content.trim(), time)
                                Toast.makeText(
                                    requireActivity(),
                                    "Note updated",
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else {
                                viewModel.addNote(
                                    NoteData(
                                        title = title, content = content.trim(),
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
                            findNavController().navigate(R.id.action_addNoteFragment_to_homeFragment)
                        } else {
                            Toast.makeText(
                                requireContext(),
                                "Fill the blank",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        true
                    }

                    else -> false
                }
            }
        }, viewLifecycleOwner)

        val callback: OnBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().navigate(R.id.action_addNoteFragment_to_homeFragment)
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }

    @RequiresApi(Build.VERSION_CODES.S)
    private fun clickEvents() {
        binding.apply {
            actionBold.setOnClickListener {
                val color = (it.background as ColorDrawable).color
                if (color == ContextCompat.getColor(requireContext(), R.color.blue)) {
                    it.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.redback))
                } else {
                    it.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.blue))
                }
                editor.setBold()
            }

            actionItalic.setOnClickListener {
                val color = (it.background as ColorDrawable).color
                if (color == ContextCompat.getColor(requireContext(), R.color.blue)) {
                    it.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.redback))
                } else {
                    it.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.blue))
                }
                editor.setItalic()
            }

            actionSubscript.setOnClickListener {
                val color = (it.background as ColorDrawable).color
                if (color == ContextCompat.getColor(requireContext(), R.color.blue)) {
                    it.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.redback))
                } else {
                    it.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.blue))
                }
                editor.setSubscript()
            }

            actionSuperscript.setOnClickListener {
                val color = (it.background as ColorDrawable).color
                if (color == ContextCompat.getColor(requireContext(), R.color.blue)) {
                    it.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.redback))
                } else {
                    it.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.blue))
                }
                editor.setSuperscript()
            }

            actionHeading1.setOnClickListener {
                val color = (it.background as ColorDrawable).color
                if (color == ContextCompat.getColor(requireContext(), R.color.blue)) {
                    it.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.redback))
                } else {
                    it.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.blue))
                }
                editor.setHeading(1)
            }

            actionHeading2.setOnClickListener {
                val color = (it.background as ColorDrawable).color
                if (color == ContextCompat.getColor(requireContext(), R.color.blue)) {
                    it.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.redback))
                } else {
                    it.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.blue))
                }
                editor.setHeading(2)
            }

            actionHeading3.setOnClickListener {
                val color = (it.background as ColorDrawable).color
                if (color == ContextCompat.getColor(requireContext(), R.color.blue)) {
                    it.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.redback))
                } else {
                    it.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.blue))
                }
                editor.setHeading(3)
            }

            actionHeading4.setOnClickListener {
                val color = (it.background as ColorDrawable).color
                if (color == ContextCompat.getColor(requireContext(), R.color.blue)) {
                    it.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.redback))
                } else {
                    it.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.blue))
                }
                editor.setHeading(4)
            }

            actionHeading5.setOnClickListener {
                val color = (it.background as ColorDrawable).color
                if (color == ContextCompat.getColor(requireContext(), R.color.blue)) {
                    it.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.redback))
                } else {
                    it.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.blue))
                }
                editor.setHeading(5)
            }

            actionHeading6.setOnClickListener {
                val color = (it.background as ColorDrawable).color
                if (color == ContextCompat.getColor(requireContext(), R.color.blue)) {
                    it.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.redback))
                } else {
                    it.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.blue))
                }
                editor.setHeading(6)
            }

            actionIndent.setOnClickListener {
                val color = (it.background as ColorDrawable).color
                if (color == ContextCompat.getColor(requireContext(), R.color.blue)) {
                    it.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.redback))
                } else {
                    it.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.blue))
                }
                editor.setIndent()
            }

            actionOutdent.setOnClickListener {
                val color = (it.background as ColorDrawable).color
                if (color == ContextCompat.getColor(requireContext(), R.color.blue)) {
                    it.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.redback))
                } else {
                    it.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.blue))
                }
                editor.setOutdent()
            }

            actionUnderline.setOnClickListener {
                val color = (it.background as ColorDrawable).color
                if (color == ContextCompat.getColor(requireContext(), R.color.blue)) {
                    it.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.redback))
                } else {
                    it.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.blue))
                }
                editor.setUnderline()
            }

            actionStrikethrough.setOnClickListener {
                val color = (it.background as ColorDrawable).color
                if (color == ContextCompat.getColor(requireContext(), R.color.blue)) {
                    it.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.redback))
                } else {
                    it.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.blue))
                }
                editor.setStrikeThrough()
            }

            actionAlignLeft.setOnClickListener {
                val color = (it.background as ColorDrawable).color
                if (color == ContextCompat.getColor(requireContext(), R.color.blue)) {
                    it.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.redback))
                } else {
                    it.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.blue))
                }
                editor.setAlignLeft()
            }

            actionAlignCenter.setOnClickListener {
                val color = (it.background as ColorDrawable).color
                if (color == ContextCompat.getColor(requireContext(), R.color.blue)) {
                    it.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.redback))
                } else {
                    it.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.blue))
                }
                editor.setAlignCenter()
            }

            actionAlignRight.setOnClickListener {
                val color = (it.background as ColorDrawable).color
                if (color == ContextCompat.getColor(requireContext(), R.color.blue)) {
                    it.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.redback))
                } else {
                    it.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.blue))
                }
                editor.setAlignRight()
            }

            actionBlockquote.setOnClickListener {
                val color = (it.background as ColorDrawable).color
                if (color == ContextCompat.getColor(requireContext(), R.color.blue)) {
                    it.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.redback))
                } else {
                    it.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.blue))
                }
                editor.setBullets()
            }
        }
    }
}