package uz.gita.noteapp.presentation.screen.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
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
                val login = edtLogin.text.toString().trim()
                val password = edtPassword.text.toString().trim()
                val passConfirm = edtPassConfirm.text.toString().trim()

                val isEmpty = login.isEmpty() && password.isEmpty() && passConfirm.isEmpty()
                if (isEmpty) {
                    Toast.makeText(this@LoginActivity, "Fill in completely", Toast.LENGTH_SHORT)
                        .show()

                } else if (!isEmpty) {
                    if (login.length < 3) {
                        Toast.makeText(
                            this@LoginActivity,
                            "Login must be more than 3 letters", Toast.LENGTH_SHORT
                        ).show()

                    } else if (password.length < 4) {
                        Toast.makeText(
                            this@LoginActivity,
                            "Password must be more than 4 letters", Toast.LENGTH_SHORT
                        ).show()

                    } else if (password != passConfirm) {
                        Toast.makeText(
                            this@LoginActivity,
                            "Password must be the same", Toast.LENGTH_SHORT
                        ).show()

                    } else {
                        viewModel.saveUser(login, password)
                        startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                    }
                }
            }
        }
    }

    override fun onPause() {
        super.onPause()
        finish()
    }
}