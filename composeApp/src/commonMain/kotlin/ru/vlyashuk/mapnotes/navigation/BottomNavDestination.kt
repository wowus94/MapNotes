package ru.vlyashuk.mapnotes.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class BottomNavDestination(val route: String) {

    @Serializable
    object Main : BottomNavDestination("main")

    @Serializable
    object Map : BottomNavDestination("catalog")


    @Serializable
    object Profile : BottomNavDestination("profile")
}
