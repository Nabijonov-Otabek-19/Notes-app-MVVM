package uz.gita.noteapp.domain.sharedpref

import android.content.Context
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor

class MyBase private constructor() {

    companion object {
        private lateinit var myBase: MyBase

        private const val SHARED_PREF = "shared_pref"
        private const val LOGIN = "login"
        private const val PASSWORD = "password"
        private const val PINCODE = "pincode"

        private lateinit var pref: SharedPreferences
        private lateinit var editor: Editor

        fun init(context: Context) {
            pref = context.getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE)
        }

        fun getInstance(): MyBase {
            if (!(::myBase.isInitialized)) {
                myBase = MyBase()
            }
            return myBase
        }
    }

    var Login: String
        get() = pref.getString(LOGIN, "").toString()
        set(value) = editor.putString(LOGIN, value).apply()

    var Password: String
        get() = pref.getString(PASSWORD, "").toString()
        set(value) = editor.putString(PASSWORD, value).apply()

    var PinCode: String
        get() = pref.getString(PINCODE, "").toString()
        set(value) = editor.putString(PINCODE, value).apply()
}