package uz.gita.noteapp_bek.domain.repository.impl

import androidx.lifecycle.LiveData
import uz.gita.noteapp_bek.data.model.NoteData
import uz.gita.noteapp_bek.data.source.local.NoteDatabase
import uz.gita.noteapp_bek.domain.repository.AppRepository

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

    private val noteDao = NoteDatabase.getInstance().getNoteDao()

    override fun addNote(note: NoteData) {
        noteDao.addNote(note.toNoteEntity())
    }

    override fun updateNote(id: Long, title: String, content: String, date: String) {
        noteDao.updateNote(id, title, content, date)
    }

    override fun archiveNote(id: Long) {
        noteDao.archiveNote(id)
    }

    override fun removeArchivedNote(id: Long) {
        noteDao.removeArchivedNote(id)
    }

    override fun pinNote(noteId: Long) {
        noteDao.pinNote(noteId)
    }

    override fun unPinNote(noteId: Long) {
        noteDao.unPinNote(noteId)
    }

    override fun changeColorNote(id: Long, color: Int) {
        noteDao.changeColorNote(id, color)
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

    override fun getArchivedNotes(): LiveData<List<NoteData>> {
        return noteDao.getArchivedNotes()
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

    override fun getSearchedNote(note: String): LiveData<List<NoteData>> {
        return noteDao.searchNote(note)
    }

    override fun deleteAllNotes() {
        noteDao.deleteAllNotes()
    }
}