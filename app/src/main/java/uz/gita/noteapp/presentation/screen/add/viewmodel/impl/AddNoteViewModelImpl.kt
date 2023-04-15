package uz.gita.noteapp.presentation.screen.add.viewmodel.impl

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.cardview.widget.CardView
import androidx.lifecycle.ViewModel
import uz.gita.noteapp.R
import uz.gita.noteapp.data.model.NoteData
import uz.gita.noteapp.domain.repository.AppRepository
import uz.gita.noteapp.domain.repository.impl.AppRepositoryImpl
import uz.gita.noteapp.presentation.screen.add.viewmodel.AddNoteViewModel
import uz.gita.noteapp.utils.Colors

class AddNoteViewModelImpl : ViewModel(), AddNoteViewModel {

    private val repository: AppRepository = AppRepositoryImpl.getInstance()

    private var color = Color.WHITE

    override fun addNote(noteData: NoteData) {
        repository.addNote(noteData)
    }

    override fun updateNote(id: Long, title: String, content: String, date: String) {
        repository.updateNote(id, title, content, date)
    }

    override fun showSetColorDialog(context: Context) {
        val colorOjb = Colors.getInstance(context)

        val dialog = Dialog(context)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.custom_colors_dialog)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val white = dialog.findViewById<CardView>(R.id.white)
        val pink = dialog.findViewById<CardView>(R.id.pink)
        val red = dialog.findViewById<CardView>(R.id.red)
        val orange = dialog.findViewById<CardView>(R.id.orange)
        val yellow = dialog.findViewById<CardView>(R.id.yellow)
        val green = dialog.findViewById<CardView>(R.id.green)
        val blue = dialog.findViewById<CardView>(R.id.blue)
        val light_pink = dialog.findViewById<CardView>(R.id.light_pink)

        red.setOnClickListener {
            color = colorOjb.red
            dialog.dismiss()
        }

        white.setOnClickListener {
            color = colorOjb.white
            dialog.dismiss()
        }

        pink.setOnClickListener {
            color = colorOjb.dark_pink
            dialog.dismiss()
        }

        orange.setOnClickListener {
            color = colorOjb.orange
            dialog.dismiss()
        }

        yellow.setOnClickListener {
            color = colorOjb.yellow
            dialog.dismiss()
        }

        green.setOnClickListener {
            color = colorOjb.green
            dialog.dismiss()
        }

        blue.setOnClickListener {
            color = colorOjb.blue
            dialog.dismiss()
        }

        light_pink.setOnClickListener {
            color = colorOjb.light_pink
            dialog.dismiss()
        }

        dialog.create()
        dialog.show()
    }

    override fun getColor() = color
}