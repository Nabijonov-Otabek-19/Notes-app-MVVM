package uz.gita.noteapp_bek.app

import android.app.Application
import uz.gita.noteapp_bek.data.source.local.NoteDatabase

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        NoteDatabase.init(this)
    }
}