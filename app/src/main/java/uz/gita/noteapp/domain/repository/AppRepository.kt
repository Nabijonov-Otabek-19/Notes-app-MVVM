package uz.gita.noteapp.domain.repository

import androidx.lifecycle.LiveData
import uz.gita.noteapp.data.model.NoteData

interface AppRepository {

    fun addNote(note: NoteData)

    fun updateNote(note: NoteData)

    fun deleteNote(note: NoteData)

    fun deleteNotes(vararg notes: NoteData)

    fun getNotes(): LiveData<List<NoteData>>

    fun getNotesInTrash(): LiveData<List<NoteData>>

    fun throwNoteToTrash(noteId: Long)

    fun recoverNote(noteId: Long)

    fun deleteAllNotesInTrash()

    fun isExistUser(): Boolean
}