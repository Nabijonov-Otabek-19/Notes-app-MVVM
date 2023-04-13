package uz.gita.noteapp.presentation.screen.add.viewmodel.impl

import androidx.lifecycle.ViewModel
import uz.gita.noteapp.data.model.NoteData
import uz.gita.noteapp.domain.repository.AppRepository
import uz.gita.noteapp.domain.repository.impl.AppRepositoryImpl
import uz.gita.noteapp.presentation.screen.add.viewmodel.AddNoteViewModel

class AddNoteViewModelImpl : ViewModel(), AddNoteViewModel {

    private val repository: AppRepository = AppRepositoryImpl.getInstance()

    override fun addNote(noteData: NoteData) {
        repository.addNote(noteData)
    }

    override fun updateNote(id: Long, title: String, content: String, date: String) {
        repository.updateNote(id, title, content, date)
    }
}