package ru.vlyashuk.mapnotes.navigation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Map
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun BottomNavigationBar(
    navController: NavHostController,
    currentRoute: String?
) {
    val bottomNavItems = listOf(
        BarItem(BottomNavDestination.Main, "Главная", Icons.Filled.Home),
        BarItem(BottomNavDestination.Map, "Карта", Icons.Filled.Map),
        BarItem(BottomNavDestination.Profile, "Профиль", Icons.Filled.AccountCircle),
    )


    NavigationBar(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(end = 6.dp),
        containerColor = MaterialTheme.colorScheme.background,
        tonalElevation = 6.dp
    ) {
        bottomNavItems.forEach { navItem ->
            val selected = currentRoute == navItem.destination.route
            NavigationBarItem(
                selected = selected,
                onClick = {
                    if (!selected) {
                        navController.navigate(navItem.destination.route) {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
                icon = {
                    Icon(
                        imageVector = navItem.image,
                        contentDescription = navItem.title
                    )
                },
                label = {
                    Text(
                        text = navItem.title,
                        color = MaterialTheme.colorScheme.onBackground,
                        fontSize = 12.sp
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = White,
                    unselectedIconColor = MaterialTheme.colorScheme.onBackground,
                    selectedIconColor = Black
                )
            )
        }
    }
}