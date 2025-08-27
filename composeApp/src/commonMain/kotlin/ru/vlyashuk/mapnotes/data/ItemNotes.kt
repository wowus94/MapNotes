package ru.vlyashuk.mapnotes.data

data class ItemNotes(
    val id: Long,
    val title: String,
    val coordinates: String,
    val description: String?
)
