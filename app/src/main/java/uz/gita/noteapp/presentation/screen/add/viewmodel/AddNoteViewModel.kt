package uz.gita.noteapp.presentation.screen.add.viewmodel

import uz.gita.noteapp.data.model.NoteData

interface AddNoteViewModel {

    fun addNote(noteData: NoteData)

    fun updateNote(id: Long, title: String, content: String, date: String)
}