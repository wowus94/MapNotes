package ru.vlyashuk.mapnotes.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class NavDestination(val route: String) {

    @Serializable
    object Main : NavDestination("main")

    @Serializable
    object Map : NavDestination("catalog")

    @Serializable
    object Profile : NavDestination("profile")

    @Serializable
    object AddNote : NavDestination("add_note")
}
