package uz.gita.noteapp.domain.repository

import androidx.lifecycle.LiveData
import uz.gita.noteapp.data.model.NoteData

interface AppRepository {

    fun addNote(note: NoteData)

    fun updateNote(id: Long, title: String, content: String, date: String)

    fun archiveNote(id: Long)

    fun removeArchivedNote(id: Long)

    fun changeColorNote(id: Long, color: Int)

    fun deleteNote(note: NoteData)

    fun deleteNotes(vararg notes: NoteData)

    fun getNotes(): LiveData<List<NoteData>>

    fun getArchivedNotes(): LiveData<List<NoteData>>

    fun getNotesInTrash(): LiveData<List<NoteData>>

    fun throwNoteToTrash(noteId: Long)

    fun recoverNote(noteId: Long)

    fun deleteAllNotesInTrash()

    fun isExistUser(): Boolean

    fun getSearchedNote(note: String): LiveData<List<NoteData>>

    fun deleteAllNotes()
}