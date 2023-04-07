package uz.gita.noteapp.presentation.screen.home.viewmodel

import androidx.lifecycle.LiveData
import uz.gita.noteapp.data.model.NoteData

interface HomeViewModel {
    val notesLiveData: LiveData<List<NoteData>>
    val openAddNoteScreenLiveData: LiveData<Unit>

    fun openAddNoteScreen()

}
