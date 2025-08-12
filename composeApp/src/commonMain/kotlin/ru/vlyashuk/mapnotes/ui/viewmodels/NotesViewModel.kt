package ru.vlyashuk.mapnotes.ui.viewmodels

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import ru.vlyashuk.mapnotes.data.ItemNotes

class NotesViewModel : ViewModel() {
    private val _notes = mutableStateListOf<ItemNotes>()
    val notes: List<ItemNotes> = _notes

    private var nextId = 0

    fun addNote(title: String, coordinates: String, description: String) {
        _notes.add(
            ItemNotes(
                id = nextId++,
                title = title,
                coordinates = coordinates,
                description = description
            )
        )
    }

    fun updateNote(id: Int, title: String, coordinates: String, description: String) {
        val index = _notes.indexOfFirst { it.id == id }
        if (index != -1) {
            _notes[index] = _notes[index].copy(
                title = title,
                coordinates = coordinates,
                description = description
            )
        }
    }
}