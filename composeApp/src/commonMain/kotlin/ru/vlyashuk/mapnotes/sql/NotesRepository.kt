package ru.vlyashuk.mapnotes.sql

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import ru.vlyashuk.mapnotes.db.ItemNotes
import ru.vlyashuk.mapnotes.db.MyDatabase

class NotesRepository(databaseDriverFactory: DatabaseDriverFactory) {
    private val database = MyDatabase(databaseDriverFactory.createDriver())
    private val queries = database.myDataBaseQueries

    fun getAllNotes(): Flow<List<ItemNotes>> =
        queries.selectAll().asFlow().mapToList(Dispatchers.IO)

    fun addNote(title: String, coordinates: String, description: String) {
        queries.insertNote(title, coordinates, description)
    }

    fun updateNote(id: Long, title: String, coordinates: String, description: String) {
        queries.updateNote(title, coordinates, description, id)
    }

    fun deleteNote(id: Long) {
        queries.deleteNote(id)
    }
}