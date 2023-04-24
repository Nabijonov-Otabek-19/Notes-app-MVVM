package uz.gita.noteapp.app

import android.app.Application
import uz.gita.noteapp.data.source.local.NoteDatabase

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        NoteDatabase.init(this)
    }
}