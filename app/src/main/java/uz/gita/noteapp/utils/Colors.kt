package uz.gita.noteapp.utils

import android.annotation.SuppressLint
import android.content.Context
import androidx.core.content.ContextCompat
import uz.gita.noteapp.R

class Colors(val context: Context) {

    companion object {

        @SuppressLint("StaticFieldLeak")
        private lateinit var colorObj: Colors

        fun getInstance(context: Context): Colors {
            if (!(::colorObj.isInitialized)) {
                colorObj = Colors(context)
            }
            return colorObj
        }
    }

    val dark_blue = ContextCompat.getColor(context, R.color.dark_blue)
    val dark_pink = ContextCompat.getColor(context, R.color.dark_pink)
    val orange = ContextCompat.getColor(context, R.color.orange)
    val yellow = ContextCompat.getColor(context, R.color.yellow)
    val green = ContextCompat.getColor(context, R.color.green)
    val blue = ContextCompat.getColor(context, R.color.blue)
    val light_pink = ContextCompat.getColor(context, R.color.light_pink)
}