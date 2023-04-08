package uz.gita.noteapp.presentation.screen.add.viewmodel

import uz.gita.noteapp.data.model.NoteData

interface AddNoteViewModel {

    fun addNote(noteData: NoteData)
}