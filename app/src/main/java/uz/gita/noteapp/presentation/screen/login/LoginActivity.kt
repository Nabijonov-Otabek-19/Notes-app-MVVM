package uz.gita.noteapp.presentation.screen.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import uz.gita.noteapp.MainActivity
import uz.gita.noteapp.databinding.ActivityLoginBinding
import uz.gita.noteapp.presentation.screen.login.viewmodel.LoginViewModel
import uz.gita.noteapp.presentation.screen.login.viewmodel.impl.LoginViewModelImpl

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels<LoginViewModelImpl>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.apply {
            btnSave.setOnClickListener {
                val login = edtLogin.text.toString()
                val password = edtPassword.text.toString()
                val passConfirm = edtPassConfirm.text.toString()

                startActivity(Intent(this@LoginActivity, MainActivity::class.java))
            }
        }
    }

    override fun onPause() {
        super.onPause()
        finish()
    }
}