package uz.gita.noteapp.presentation.screen.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import uz.gita.noteapp.MainActivity
import uz.gita.noteapp.databinding.ActivitySplashBinding
import uz.gita.noteapp.presentation.screen.login.LoginActivity
import uz.gita.noteapp.presentation.screen.splash.viewmodel.SplashViewModel
import uz.gita.noteapp.presentation.screen.splash.viewmodel.impl.SplashViewModelImpl

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private lateinit var viewModel: SplashViewModel
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        viewModel = SplashViewModelImpl()

        val time = 1500L

        Handler(Looper.getMainLooper()).postDelayed({

            if (viewModel.isExistUser()) {
                Toast.makeText(this, "User exist", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))

            } else {
                Toast.makeText(this, "User is not exist", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
            }
        }, time)
    }

    override fun onPause() {
        super.onPause()
        finish()
    }
}