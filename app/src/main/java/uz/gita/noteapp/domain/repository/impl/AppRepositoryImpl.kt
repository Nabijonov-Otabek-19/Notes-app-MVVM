package uz.gita.noteapp.domain.repository.impl

import androidx.lifecycle.LiveData
import uz.gita.noteapp.data.model.NoteData
import uz.gita.noteapp.data.source.local.NoteDatabase
import uz.gita.noteapp.domain.repository.AppRepository
import uz.gita.noteapp.domain.sharedpref.MyBase

class AppRepositoryImpl private constructor() : AppRepository {

    companion object {
        private lateinit var repository: AppRepositoryImpl

        fun getInstance(): AppRepositoryImpl {
            if (!(Companion::repository.isInitialized)) {
                repository = AppRepositoryImpl()
            }
            return repository
        }
    }

    private val myBase = MyBase.getInstance()
    private val noteDao = NoteDatabase.getInstance().getNoteDao()

    override fun addNote(note: NoteData) {
        noteDao.addNote(note.toNoteEntity())
    }

    override fun updateNote(note: NoteData) {
        noteDao.updateNote(note.toNoteEntity())
    }

    override fun deleteNote(note: NoteData) {
        noteDao.deleteNote(note.toNoteEntity())
    }

    override fun deleteNotes(vararg notes: NoteData) {
        val list = notes.map {
            it.toNoteEntity()
        }.toTypedArray()

        noteDao.deleteNotes(*list)
    }

    override fun getNotes(): LiveData<List<NoteData>> {
        return noteDao.getNotes()
    }

    override fun getNotesInTrash(): LiveData<List<NoteData>> {
        return noteDao.getNotesInTrash()
    }

    override fun throwNoteToTrash(noteId: Long) {
        noteDao.throwNoteToTrash(noteId)
    }

    override fun recoverNote(noteId: Long) {
        noteDao.recoverNote(noteId)
    }

    override fun deleteAllNotesInTrash() {
        noteDao.deleteAllNotesInTrash()
    }

    override fun isExistUser(): Boolean {
        return myBase.Login.isNotEmpty()
    }
}