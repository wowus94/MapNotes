package ru.vlyashuk.mapnotes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import org.jetbrains.compose.ui.tooling.preview.Preview
import ru.vlyashuk.mapnotes.navigation.BottomNavigationBar
import ru.vlyashuk.mapnotes.navigation.NavDestination
import ru.vlyashuk.mapnotes.theme.AppTheme
import ru.vlyashuk.mapnotes.ui.screens.AddNoteScreen
import ru.vlyashuk.mapnotes.ui.screens.EditNoteScreen
import ru.vlyashuk.mapnotes.ui.screens.MainScreen
import ru.vlyashuk.mapnotes.ui.screens.MapScreen
import ru.vlyashuk.mapnotes.ui.screens.ProfileScreen
import ru.vlyashuk.mapnotes.ui.viewmodels.NotesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
internal fun App() = AppTheme {

    val navController = rememberNavController()
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route

    val notesViewModel: NotesViewModel = viewModel()

    val bottomBarRoutes = listOf(
        NavDestination.Main.route,
        NavDestination.Map.route,
        NavDestination.Profile.route
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
                    startDestination = NavDestination.Main.route
                ) {
                    composable(NavDestination.Main.route) {
                        MainScreen(
                            notes = notesViewModel.notes,
                            onAddClick = {
                                navController.navigate(NavDestination.AddNote.route)
                            },
                            navController = navController
                        )
                    }
                    composable(NavDestination.AddNote.route) {
                        AddNoteScreen(
                            onSave = { title, coords, description ->
                                notesViewModel.addNote(title, coords, description)
                                navController.popBackStack()
                            },
                            onCancel = { navController.popBackStack() }
                        )
                    }
                    composable(NavDestination.Map.route) {
                        MapScreen()
                    }
                    composable(NavDestination.Profile.route) {
                        ProfileScreen()
                    }
                    composable<NavDestination.EditNote> { backStackEntry ->
                        val args = backStackEntry.toRoute<NavDestination.EditNote>()
                        val note = notesViewModel.notes.firstOrNull { it.id == args.id }
                        if (note != null) {
                            EditNoteScreen(
                                note = note,
                                onSave = { title, coords, description ->
                                    notesViewModel.updateNote(note.id, title, coords, description)
                                    navController.popBackStack()
                                },
                                onCancel = { navController.popBackStack() }
                            )
                        }
                    }
                }
            }
        }
    }
}