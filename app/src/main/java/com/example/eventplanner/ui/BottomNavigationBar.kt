package com.example.eventplanner.ui

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.eventplanner.Routes

@Composable
fun BottomNavigationBar(
    navController: NavController,
)
{
    val items = listOf(
        Routes.EventList,
        Routes.AddEvent
    )

    val itemRoutes = remember(items)
    {
        items.map { it.routeName }
    }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    if (itemRoutes.contains(currentRoute))
    {
        NavigationBar(
            modifier = Modifier//.height(60.dp)
        ) {
            items.forEach { item ->
                NavigationBarItem(
                    icon= {
                        Icon(modifier = Modifier.size(24.dp),painter = painterResource(id = item.navIconId), contentDescription = "")
                    },
                    selected = item.routeName == currentRoute,
                    label = { Text(text = item.navItemText) },
                    onClick = {
                        navController.navigateToNavBarRoute(item.routeName)
                    },
                    alwaysShowLabel = true
                )
            }
        }
    }
}

fun NavController.navigateToNavBarRoute(route: String) {
    navigate(route)
    {
        // Pop up to the start destination of the graph to
        // avoid building up a large stack of destinations
        // on the back stack as users select items
        graph.startDestinationRoute?.let { screen_route ->
            popUpTo(screen_route) {
                saveState = false
            }
        }
        // Avoid multiple copies of the same destination when
        // reselecting the same item
        launchSingleTop = true
        // Restore state when reselecting a previously selected item
        restoreState = false
    }
}