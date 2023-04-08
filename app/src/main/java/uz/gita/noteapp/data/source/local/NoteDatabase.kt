package uz.gita.noteapp.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import uz.gita.noteapp.data.source.local.converter.DateConverter
import uz.gita.noteapp.data.source.local.dao.NoteDao
import uz.gita.noteapp.data.source.local.entity.NoteEntity

@Database(entities = [NoteEntity::class], version=1)
@TypeConverters(DateConverter::class)
abstract class NoteDatabase: RoomDatabase() {

    abstract fun getNoteDao(): NoteDao

    companion object {
        private lateinit var database: NoteDatabase

        private const val DATABASE_NAME = "NoteDB.db"

        fun init(context: Context) {
            if(!(Companion::database.isInitialized)) {
                database = Room.databaseBuilder(
                    context,
                    NoteDatabase::class.java,
                    DATABASE_NAME
                )
                    .allowMainThreadQueries()
                    .build()
            }
        }

        fun getInstance() = database

    }
}