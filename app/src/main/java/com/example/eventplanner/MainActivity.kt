package com.example.eventplanner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.eventplanner.ui.BottomNavigationBar
import com.example.eventplanner.ui.add_event.AddEventEffect
import com.example.eventplanner.ui.add_event.AddEventScreen
import com.example.eventplanner.ui.add_event.AddEventViewModel
import com.example.eventplanner.ui.events_list.EventListViewModel
import com.example.eventplanner.ui.events_list.EventsListScreen
import com.example.eventplanner.ui.events_map.MapScreen
import com.example.eventplanner.ui.events_map.MapViewModel
import com.example.eventplanner.ui.theme.EventPlannerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EventPlannerTheme {
                val navController = rememberNavController()
                Scaffold(bottomBar = { BottomNavigationBar(navController = navController) }) { padding ->
                    NavHost(navController = navController, startDestination = Routes.AddEvent.routeName) {
                        composable(route = Routes.EventList.routeName) {
                            EventListDestination(modifier = Modifier.padding(padding))
                        }
                        composable(route = Routes.AddEvent.routeName) {
                            AddEventDestination(
                                modifier = Modifier.padding(padding),
                                onNavigateToEventList = {
                                    navController.navigate(Routes.EventList.routeName)
                                },
                            )
                        }
                        composable(route = Routes.Map.routeName) {
                            MapDestination(modifier = Modifier.padding(padding))
                        }
                    }
                }
            }
        }
    }
}

sealed class Routes(val routeName: String, val navItemText: String, val navIconId: Int) {
    object EventList: Routes(routeName = "EventList", navItemText = "Lista Wydarzeń", navIconId = R.drawable.calendar_icon)
    object AddEvent: Routes(routeName = "AddEvent", navItemText = "Dodawanie wydarzeń", navIconId = R.drawable.calendar_icon)
    object Map: Routes(routeName = "Map", navItemText = "Mapa wydarzeń", navIconId = R.drawable.calendar_icon)
}

@Composable
private fun EventListDestination(modifier: Modifier) {
    val eventListViewModel = hiltViewModel<EventListViewModel>()
    val eventListState = eventListViewModel.state.collectAsState()
    EventsListScreen(
        modifier = modifier,
        state = eventListState.value
    )
}

@Composable
private fun HandleAddEventEffect(
    addEventViewModel: AddEventViewModel,
    action: (AddEventEffect) -> Unit
) {
    val currentEffect = addEventViewModel.effect.collectAsState().value
    if(currentEffect != null) {
        action(currentEffect)
        addEventViewModel.clearEffect()
    }
}

@Composable
private fun AddEventDestination(
    modifier: Modifier,
    onNavigateToEventList: () -> Unit,
) {
    val addEventViewModel = hiltViewModel<AddEventViewModel>()
    val addEventState = addEventViewModel.state.collectAsState()

    AddEventScreen(
        modifier = modifier,
        state = addEventState.value,
        onShowDateDialog = { addEventViewModel.onShowDialog() },
        onDateChange = { addEventViewModel.onDateChange(it) },
        onHideDialog = { addEventViewModel.onHideDialog() },
        onChangeTitle = { addEventViewModel.onChangeTitle(it) },
        onChangePlace = { addEventViewModel.onChangePlace(it) },
        onChangeImageUrl = { addEventViewModel.onChangeImageUrl(it) },
        onAddEventClick = { addEventViewModel.onAddEventClick() },
        onNavigateToEventList = { onNavigateToEventList() },
    )

    HandleAddEventEffect(addEventViewModel = addEventViewModel) { effect ->
        when(effect) {
            is AddEventEffect.NavigateToEventList -> {
                onNavigateToEventList()
            }
        }
    }
}

@Composable
private fun MapDestination(modifier: Modifier) {
    val mapViewModel = hiltViewModel<MapViewModel>()
    val mapState = mapViewModel.state.collectAsState()

    MapScreen(
        modifier = modifier,
        state = mapState.value,
        onChooseMarker = {
            mapViewModel.onChooseEvent(newChosenMarker = it)
        }
    )
}