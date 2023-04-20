package uz.gita.noteapp.presentation.screen.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import uz.gita.noteapp.MainActivity
import uz.gita.noteapp.databinding.ActivitySplashBinding
import uz.gita.noteapp.utils.typeWrite

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.apply {
            txt.typeWrite(this@SplashActivity, "Start Note", 35L)
        }

        val time = 1500L

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
        }, time)
    }

    override fun onPause() {
        super.onPause()
        finish()
    }
}