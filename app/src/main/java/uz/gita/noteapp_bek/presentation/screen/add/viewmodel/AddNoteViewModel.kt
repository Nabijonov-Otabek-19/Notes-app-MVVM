package uz.gita.noteapp_bek.presentation.screen.add.viewmodel

import android.content.Context
import uz.gita.noteapp_bek.data.model.NoteData

interface AddNoteViewModel {

    fun addNote(noteData: NoteData)

    fun updateNote(id: Long, title: String, content: String, date: String)

    fun showSetColorDialog(context: Context)

    fun getColor(): Int
}