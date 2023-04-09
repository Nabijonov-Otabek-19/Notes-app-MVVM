package uz.gita.noteapp.presentation.screen.splash.viewmodel.impl

import androidx.lifecycle.ViewModel
import uz.gita.noteapp.domain.repository.AppRepository
import uz.gita.noteapp.domain.repository.impl.AppRepositoryImpl
import uz.gita.noteapp.presentation.screen.splash.viewmodel.SplashViewModel

class SplashViewModelImpl : SplashViewModel, ViewModel() {

    private val repository: AppRepository = AppRepositoryImpl.getInstance()

    override fun isExistUser(): Boolean {
        return repository.isExistUser()
    }
}