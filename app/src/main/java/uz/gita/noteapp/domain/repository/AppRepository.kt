package uz.gita.noteapppractice.domain.repository

import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import uz.gita.noteapppractice.data.model.NoteData
import uz.gita.noteapppractice.data.source.local.entity.NoteEntity

interface AppRepository {

    fun addNote(note: NoteData)

    fun updateNote(note: NoteData)

    fun deleteNote(note: NoteData)

    fun deleteNotes(vararg notes: NoteData)

    fun getNotes(): LiveData<List<NoteData>>

    fun getNotesInTrash(): LiveData<List<NoteData>>
}