package uz.gita.noteapp.app

import android.app.Application
import uz.gita.noteapp.data.source.local.NoteDatabase
import uz.gita.noteapp.domain.sharedpref.MyBase

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        MyBase.init(this)
        NoteDatabase.init(this)
    }
}