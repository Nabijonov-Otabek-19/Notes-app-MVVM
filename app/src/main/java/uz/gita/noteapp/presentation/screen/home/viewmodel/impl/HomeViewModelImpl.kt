package uz.gita.noteapp.presentation.screen.home.viewmodel.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.gita.noteapppractice.data.model.NoteData
import uz.gita.noteapppractice.domain.repository.AppRepository
import uz.gita.noteapp.domain.repository.impl.AppRepositoryImpl
import uz.gita.noteapp.presentation.screen.home.viewmodel.HomeViewModel

class HomeViewModelImpl: ViewModel(), HomeViewModel {
    private val repository: AppRepository = AppRepositoryImpl.getInstance()

    override val notesLiveData: LiveData<List<NoteData>>
        get() = repository.getNotes()

    override val openAddNoteScreenLiveData = MutableLiveData<Unit>()

    override fun openAddNoteScreen() {
        openAddNoteScreenLiveData.value = Unit
    }
}
