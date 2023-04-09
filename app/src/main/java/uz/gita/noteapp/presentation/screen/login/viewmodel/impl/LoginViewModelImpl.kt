package uz.gita.noteapp.presentation.screen.login.viewmodel.impl

import androidx.lifecycle.ViewModel
import uz.gita.noteapp.domain.repository.AppRepository
import uz.gita.noteapp.domain.repository.impl.AppRepositoryImpl
import uz.gita.noteapp.presentation.screen.login.viewmodel.LoginViewModel

class LoginViewModelImpl : LoginViewModel, ViewModel() {

    private val repository: AppRepository = AppRepositoryImpl.getInstance()

    override fun saveUser(login: String, password: String) {
        repository.saveUser(login, password)
    }
}