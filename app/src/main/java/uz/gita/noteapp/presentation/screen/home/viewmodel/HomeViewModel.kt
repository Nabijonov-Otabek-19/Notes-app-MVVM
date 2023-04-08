package uz.gita.noteapp.presentation.screen.home.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import uz.gita.noteapp.data.model.NoteData

interface HomeViewModel {
    val notesLiveData: LiveData<List<NoteData>>
    val openAddNoteScreenLiveData: LiveData<Unit>

    fun showDeleteDialog(context: Context, noteID: Long)
    fun openAddNoteScreen()
}