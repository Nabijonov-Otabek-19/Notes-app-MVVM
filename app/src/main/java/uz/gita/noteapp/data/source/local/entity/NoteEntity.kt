package uz.gita.noteapppractice.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import uz.gita.noteapppractice.data.model.NoteData
import java.util.Date

@Entity(tableName = "Notes")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val content: String,
    @ColumnInfo(name = "created_at")
    val createdAt: Date = Date(),
    @ColumnInfo(name = "on_trash")
    val onTrash: Int = 0
) {
    fun toNoteData() = NoteData(
        id, title, content, createdAt, onTrash
    )
}
