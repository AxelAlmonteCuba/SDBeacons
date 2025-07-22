package com.example.sistemadedetecciondebeacons.beaconConfig

import  kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


object BeaconDataProvider {
    private val _closestBeacon = MutableStateFlow<BeaconData?>(null)
    val closestBeacon: StateFlow<BeaconData?> = _closestBeacon

    fun updateClosestBeacon(beacon: BeaconData?) {
        _closestBeacon.value = beacon
    }
}
