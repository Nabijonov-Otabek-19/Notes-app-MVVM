package uz.gita.noteapp.data.source.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import uz.gita.noteapp.data.model.NoteData
import uz.gita.noteapp.data.source.local.entity.NoteEntity

@Dao
interface NoteDao {

    @Insert
    fun addNote(note: NoteEntity)

    @Update
    fun updateNote(note: NoteEntity)

    @Delete
    fun deleteNote(note: NoteEntity)

    @Delete
    fun deleteNotes(vararg notes: NoteEntity)

    @Query("SELECT * FROM Notes WHERE on_trash=0")
    fun getNotes(): LiveData<List<NoteData>>

    @Query("SELECT * FROM Notes WHERE on_trash=1")
    fun getNotesInTrash(): LiveData<List<NoteData>>

    @Query("UPDATE Notes set on_trash=1 WHERE id = :noteID")
    fun throwNoteToTrash(noteID: Long)

    @Query("UPDATE Notes set on_trash=0 WHERE id = :noteID")
    fun recoverNote(noteID: Long)

    @Query("DELETE FROM Notes WHERE on_trash = 1")
    fun deleteAllNotesInTrash()

    @Query("SELECT * FROM Notes WHERE title LIKE :note")
    fun searchNote(note: String): LiveData<List<NoteData>>
}