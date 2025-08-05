package ru.vlyashuk.mapnotes.navigation

import androidx.compose.ui.graphics.vector.ImageVector

data class BarItem(
    val destination: BottomNavDestination,
    val title: String,
    val image: ImageVector
)
