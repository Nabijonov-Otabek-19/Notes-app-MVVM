package uz.gita.noteapp_bek.utils

import android.graphics.Paint
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


fun TextView.showStrikeThrough(show: Boolean) {
    paintFlags =
        if (show) paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        else paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
}

fun TextView.typeWrite(lifecycleOwner: LifecycleOwner, text: String, intervalMs: Long) {
    this@typeWrite.text = ""
    lifecycleOwner.lifecycleScope.launch {
        repeat(text.length) {
            delay(intervalMs)
            this@typeWrite.text = text.take(it + 1)
        }
    }
}