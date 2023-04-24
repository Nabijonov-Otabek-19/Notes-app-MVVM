package uz.gita.noteapp_bek.data.model

import androidx.room.ColumnInfo
import uz.gita.noteapp_bek.data.source.local.entity.NoteEntity


data class NoteData(
    val id: Long = 0,
    val title: String,
    val content: String,
    @ColumnInfo(name = "created_at")
    val createdAt: String,
    @ColumnInfo(name = "on_trash")
    val onTrash: Int = 0,
    val archived: Int = 0,
    val pinned: Int,
    val color: Int

) : java.io.Serializable {
    fun toNoteEntity(): NoteEntity = NoteEntity(
        id, title, content, createdAt, onTrash, archived, pinned, color
    )
}
