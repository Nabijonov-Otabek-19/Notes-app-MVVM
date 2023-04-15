package uz.gita.noteapp.presentation.screen.add.viewmodel

import android.content.Context
import uz.gita.noteapp.data.model.NoteData

interface AddNoteViewModel {

    fun addNote(noteData: NoteData)

    fun updateNote(id: Long, title: String, content: String, date: String)

    fun showSetColorDialog(context: Context)

    fun getColor(): Int
}