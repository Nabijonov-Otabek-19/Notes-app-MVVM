package uz.gita.noteapp.presentation.screen.trash.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import uz.gita.noteapp.data.model.NoteData

interface TrashViewModel {

    val notesTrashLiveData: LiveData<List<NoteData>>

    fun showRecoverDialog(context: Context, noteID: Long)
    fun showDeleteAllDialog(context: Context)
}
