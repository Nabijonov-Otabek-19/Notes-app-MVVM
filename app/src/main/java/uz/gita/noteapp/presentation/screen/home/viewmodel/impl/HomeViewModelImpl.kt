package uz.gita.noteapp.presentation.screen.home.viewmodel.impl

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.cardview.widget.CardView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import uz.gita.noteapp.R
import uz.gita.noteapp.data.model.NoteData
import uz.gita.noteapp.domain.repository.AppRepository
import uz.gita.noteapp.domain.repository.impl.AppRepositoryImpl
import uz.gita.noteapp.presentation.screen.home.viewmodel.HomeViewModel
import uz.gita.noteapp.utils.Colors

class HomeViewModelImpl : ViewModel(), HomeViewModel {
    private val repository: AppRepository = AppRepositoryImpl.getInstance()

    override val notesLiveData: LiveData<List<NoteData>>
        get() = repository.getNotes()

    override val openAddNoteScreenLiveData = MutableLiveData<Unit>()


    override fun showDeleteDialog(context: Context, noteID: Long) {
        val dialog = Dialog(context)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.custom_delete_dialog)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val btnNo: AppCompatButton = dialog.findViewById(R.id.btnNo)
        val btnYes: AppCompatButton = dialog.findViewById(R.id.btnYes)

        btnNo.setOnClickListener { dialog.dismiss() }

        btnYes.setOnClickListener {
            repository.throwNoteToTrash(noteID)
            dialog.dismiss()
        }
        dialog.create()
        dialog.show()
    }

    override fun showBottomSheetDialog(context: Context, noteID: Long) {
        val dialog = BottomSheetDialog(context)
        dialog.setCancelable(false)
        val view = dialog.layoutInflater.inflate(R.layout.event_dialog, null)

        val btnLine = view.findViewById<LinearLayoutCompat>(R.id.line)
        val btnColor = view.findViewById<LinearLayoutCompat>(R.id.lineColor)
        val btnArchive = view.findViewById<LinearLayoutCompat>(R.id.lineArchive)
        val btnDelete = view.findViewById<LinearLayoutCompat>(R.id.lineDelete)

        btnLine.setOnClickListener {

            dialog.dismiss()
        }

        btnColor.setOnClickListener {
            showChangeColorDialog(context, noteID)
            dialog.dismiss()
        }

        btnArchive.setOnClickListener {
            Toast.makeText(context, "Archived", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }

        btnDelete.setOnClickListener {
            repository.throwNoteToTrash(noteID)
            Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }

        dialog.setContentView(view)
        dialog.show()
    }

    override fun showChangeColorDialog(context: Context, noteID: Long) {
        val colorOjb = Colors.getInstance(context)

        val dialog = Dialog(context)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.custom_colors_dialog)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val dark_blue = dialog.findViewById<CardView>(R.id.dark_blue)
        val pink = dialog.findViewById<CardView>(R.id.pink)
        val orange = dialog.findViewById<CardView>(R.id.orange)
        val yellow = dialog.findViewById<CardView>(R.id.yellow)
        val green = dialog.findViewById<CardView>(R.id.green)
        val blue = dialog.findViewById<CardView>(R.id.blue)
        val light_pink = dialog.findViewById<CardView>(R.id.light_pink)

        dark_blue.setOnClickListener {
            repository.changeColorNote(noteID, colorOjb.dark_blue)
            dialog.dismiss()
        }

        pink.setOnClickListener {
            repository.changeColorNote(noteID, colorOjb.dark_pink)
            dialog.dismiss()
        }

        orange.setOnClickListener {
            repository.changeColorNote(noteID, colorOjb.orange)
            dialog.dismiss()
        }

        yellow.setOnClickListener {
            repository.changeColorNote(noteID, colorOjb.yellow)
            dialog.dismiss()
        }

        green.setOnClickListener {
            repository.changeColorNote(noteID, colorOjb.green)
            dialog.dismiss()
        }

        blue.setOnClickListener {
            repository.changeColorNote(noteID, colorOjb.blue)
            dialog.dismiss()
        }

        light_pink.setOnClickListener {
            repository.changeColorNote(noteID, colorOjb.light_pink)
            dialog.dismiss()
        }

        dialog.create()
        dialog.show()
    }

    override fun openAddNoteScreen() {
        openAddNoteScreenLiveData.value = Unit
    }

    override fun searchNote(note: String): LiveData<List<NoteData>> {
        return repository.getSearchedNote(note)
    }
}
