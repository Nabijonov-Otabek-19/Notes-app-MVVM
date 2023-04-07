package uz.gita.noteapppractice.data.source.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import uz.gita.noteapppractice.data.model.NoteData
import uz.gita.noteapppractice.data.source.local.entity.NoteEntity

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
}