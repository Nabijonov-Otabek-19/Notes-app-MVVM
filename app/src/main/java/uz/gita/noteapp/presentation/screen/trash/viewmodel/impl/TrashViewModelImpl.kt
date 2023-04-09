package uz.gita.noteapp.presentation.screen.trash.viewmodel.impl

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.widget.AppCompatButton
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import uz.gita.noteapp.R
import uz.gita.noteapp.data.model.NoteData
import uz.gita.noteapp.domain.repository.AppRepository
import uz.gita.noteapp.domain.repository.impl.AppRepositoryImpl
import uz.gita.noteapp.presentation.screen.trash.viewmodel.TrashViewModel

class TrashViewModelImpl : ViewModel(), TrashViewModel {

    private val repository: AppRepository = AppRepositoryImpl.getInstance()

    override val notesTrashLiveData: LiveData<List<NoteData>>
        get() = repository.getNotesInTrash()

    override fun showRecoverDialog(context: Context, noteID: Long) {
        val dialog = Dialog(context)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.custom_recover_dialog)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val btnNo: AppCompatButton = dialog.findViewById(R.id.btnNo)
        val btnYes: AppCompatButton = dialog.findViewById(R.id.btnYes)

        btnNo.setOnClickListener { dialog.dismiss() }

        btnYes.setOnClickListener {
            repository.recoverNote(noteID)
            dialog.dismiss()
        }
        dialog.create()
        dialog.show()
    }

    override fun showDeleteAllDialog(context: Context) {
        val dialog = Dialog(context)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.custom_delete_all_dialog)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val btnNo: AppCompatButton = dialog.findViewById(R.id.btnNo)
        val btnYes: AppCompatButton = dialog.findViewById(R.id.btnYes)

        btnNo.setOnClickListener { dialog.dismiss() }

        btnYes.setOnClickListener {
            repository.deleteAllNotesInTrash()
            dialog.dismiss()
        }
        dialog.create()
        dialog.show()
    }
}