package com.example.eventplanner.ui.events_map


import android.Manifest
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.example.eventplanner.ui.models.EventMarker
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.android.gms.maps.CameraUpdate
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLngBounds
import com.google.maps.android.compose.*
import kotlinx.coroutines.delay

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun MyGoogleMap(
    modifier: Modifier = Modifier,
    cameraPositionState: CameraPositionState = rememberCameraPositionState(),
    markers: List<EventMarker>,
    onMarkerClick: (EventMarker) -> Unit = {},
)
{
    val showLocationButtons = run {
        val locationPermissionState = rememberPermissionState(permission = Manifest.permission.ACCESS_FINE_LOCATION)
        val hasPermission = locationPermissionState.status.isGranted

        if(!hasPermission)
        {
            val lifecycleOwner = LocalLifecycleOwner.current
            DisposableEffect(key1 = lifecycleOwner, effect = {
                val eventObserver = LifecycleEventObserver { _, event ->
                    when (event) {
                        Lifecycle.Event.ON_START -> {
                            locationPermissionState.launchPermissionRequest()
                        }
                        else -> {}
                    }
                }
                lifecycleOwner.lifecycle.addObserver(eventObserver)

                onDispose {
                    lifecycleOwner.lifecycle.removeObserver(eventObserver)
                }
            })
        }
        hasPermission
    }

    val isMapLoaded = remember {
        mutableStateOf(false)
    }

    val mapProperties by remember(showLocationButtons) { mutableStateOf(MapProperties(isMyLocationEnabled = showLocationButtons)) }
    val uiSettings by remember(showLocationButtons) { mutableStateOf(MapUiSettings(myLocationButtonEnabled = showLocationButtons)) }
    GoogleMap(
        properties = mapProperties,
        uiSettings = uiSettings,
        modifier = modifier,
        onMapLoaded = { isMapLoaded.value = true },
        cameraPositionState = cameraPositionState,
    )
    {
        val context = LocalContext.current
        markers.forEach {
            CustomMapMarker(
                context = context,
                position = it.position,
                title = it.eventTittle,
                iconResourceId = it.iconId
            )
            {
                onMarkerClick.invoke(it)
                true
            }
        }

    }

    val markersPositions = remember(markers) {
        markers.map { it.position }
    }

    LaunchedEffect(markersPositions) {
        if(markers.isNotEmpty()){
            try {
                //Wait for map to be ready if it's not
                while (!isMapLoaded.value)
                    delay(5)
                val newBounds = getBoundsOfMarkers(markers)
                newBounds?.let {
                    //Animate to marker bounds
                    cameraPositionState.move(newBounds)
                }
            }
            catch (e: Exception)
            {
                e.printStackTrace()
            }
        }
    }
}

private fun getBoundsOfMarkers(markers: List<EventMarker>): CameraUpdate? {
    return try {
        val builder = LatLngBounds.Builder()
        markers.forEach {
            builder.include(it.position)
        }
        CameraUpdateFactory.newLatLngBounds(builder.build(), 150)
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}