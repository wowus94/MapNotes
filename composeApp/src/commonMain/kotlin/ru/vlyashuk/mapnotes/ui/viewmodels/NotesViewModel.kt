package ru.vlyashuk.mapnotes.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import ru.vlyashuk.mapnotes.db.ItemNotes
import ru.vlyashuk.mapnotes.sql.NotesRepository

class NotesViewModel(
    private val repository: NotesRepository
) : ViewModel() {

    val notes: StateFlow<List<ItemNotes>> =
        repository.getAllNotes()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = emptyList()
            )

    fun addNote(title: String, coordinates: String, description: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addNote(title, coordinates, description)
        }
    }

    fun updateNote(id: Long, title: String, coordinates: String, description: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateNote(id, title, coordinates, description)
        }
    }

    fun deleteNote(id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteNote(id)
        }
    }
}