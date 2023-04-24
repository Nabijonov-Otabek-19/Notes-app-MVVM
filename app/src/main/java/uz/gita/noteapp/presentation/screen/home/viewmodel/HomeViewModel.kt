package uz.gita.noteapp.presentation.screen.home.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import uz.gita.noteapp.data.model.NoteData

interface HomeViewModel {
    val notesLiveData: LiveData<List<NoteData>>
    val openAddNoteScreenLiveData: LiveData<Unit>

    fun showBottomSheetDialog(context: Context, noteID: Long, isPin: Int)
    fun showChangeColorDialog(context: Context, noteID: Long)
    fun openAddNoteScreen()

    fun pinNote(noteID: Long)
    fun unPinNote(noteID: Long)

    fun searchNote(note: String): LiveData<List<NoteData>>
}