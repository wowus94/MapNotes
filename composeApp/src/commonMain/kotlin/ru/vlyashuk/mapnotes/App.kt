package ru.vlyashuk.mapnotes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import org.jetbrains.compose.ui.tooling.preview.Preview
import ru.vlyashuk.mapnotes.navigation.BottomNavDestination
import ru.vlyashuk.mapnotes.navigation.BottomNavigationBar
import ru.vlyashuk.mapnotes.screens.MainScreen
import ru.vlyashuk.mapnotes.screens.MapScreen
import ru.vlyashuk.mapnotes.screens.ProfileScreen
import ru.vlyashuk.mapnotes.theme.AppTheme

@Preview
@Composable
internal fun App() = AppTheme {

    val navController = rememberNavController()
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route

    val bottomBarRoutes = listOf(
        BottomNavDestination.Main.route,
        BottomNavDestination.Map.route,
        BottomNavDestination.Profile.route
    )


    Scaffold(
        bottomBar = {
            if (currentRoute in bottomBarRoutes) {
                BottomNavigationBar(
                    navController = navController,
                    currentRoute = currentRoute
                )
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background)
                .padding(
                    bottom = innerPadding.calculateBottomPadding(),
                    top = innerPadding.calculateTopPadding()
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = MaterialTheme.colorScheme.background)
            ) {

                NavHost(
                    navController = navController,
                    startDestination = BottomNavDestination.Main.route
                ) {
                    composable(BottomNavDestination.Main.route) {
                        MainScreen()
                    }
                    composable(BottomNavDestination.Map.route) {
                        MapScreen()
                    }
                    composable(BottomNavDestination.Profile.route) {
                        ProfileScreen()
                    }
                }
            }
        }
    }
}