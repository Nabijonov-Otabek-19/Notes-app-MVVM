package uz.gita.noteapp.data.source.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import uz.gita.noteapp.data.model.NoteData
import uz.gita.noteapp.data.source.local.entity.NoteEntity

@Dao
interface NoteDao {

    @Insert
    fun addNote(note: NoteEntity)

    @Delete
    fun deleteNote(note: NoteEntity)

    @Delete
    fun deleteNotes(vararg notes: NoteEntity)

    @Query("UPDATE Notes Set title = :title, content = :content, created_at = :date WHERE id =:id")
    fun updateNote(id: Long, title: String, content: String, date: String)

    @Query("UPDATE Notes set color = :color WHERE id = :noteID")
    fun changeColorNote(noteID: Long, color: Int)

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

    @Query("SELECT * FROM Notes WHERE title LIKE :note AND on_trash=0")
    fun searchNote(note: String): LiveData<List<NoteData>>

    @Query("DELETE FROM Notes")
    fun deleteAllNotes()
}