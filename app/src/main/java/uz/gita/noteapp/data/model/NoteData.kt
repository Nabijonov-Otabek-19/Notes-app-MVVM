package uz.gita.noteapppractice.data.model

import androidx.room.ColumnInfo
import uz.gita.noteapppractice.data.source.local.entity.NoteEntity
import java.util.*

data class NoteData(
    val id: Long = 0,
    val title: String,
    val content: String,
    @ColumnInfo(name = "created_at")
    val createdAt: Date = Date(),
    @ColumnInfo(name = "on_trash")
    val onTrash: Int = 0
) {
    fun toNoteEntity(): NoteEntity = NoteEntity(
        id, title, content, createdAt, onTrash
    )
}
