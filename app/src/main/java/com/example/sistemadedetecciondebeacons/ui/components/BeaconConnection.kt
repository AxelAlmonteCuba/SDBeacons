package com.example.sistemadedetecciondebeacons.ui.components

import  android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sistemadedetecciondebeacons.beaconConfig.BeaconViewModel
import com.example.sistemadedetecciondebeacons.viewModel.MicroViewModel
import kotlinx.coroutines.delay

@Composable
fun BeaconConnection(viewModel: MicroViewModel = viewModel(), user_Name: String ) {
    val beaconViewModel: BeaconViewModel = viewModel()
    val closestBeacon by beaconViewModel.closestBeacon.collectAsState()



    LaunchedEffect(closestBeacon) { // Solo se ejecuta cuando cambia el beacon
        closestBeacon?.let { beacon ->
            Log.d("BEACON", "Nuevo Beacon Detectado: Minor ${beacon.minor}, RSSI ${beacon.rssi}, Distancia ${beacon.distance}")

            viewModel.startUpdatingUser(user_Name, beacon.minor, beacon.rssi, beacon.distance)
            delay(5000) // Esperar antes de la siguiente actualización
        }
    }
}