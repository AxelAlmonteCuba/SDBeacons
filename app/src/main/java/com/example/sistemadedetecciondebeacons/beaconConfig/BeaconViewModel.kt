/*
* Código desarrollado por:
* Eduardo Franshua Durand Obando
* Axel Frank Almonte Cuba
*
* Desarrollado en el Centro de Investigación (CIALE)
* de la Universidad Nacional de San Agustín.
*
*/

package com.example.sistemadedetecciondebeacons.beaconConfig


import android.app.Application
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.RemoteException
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.altbeacon.beacon.BeaconConsumer
import org.altbeacon.beacon.BeaconManager
import org.altbeacon.beacon.BeaconParser
import org.altbeacon.beacon.Region

class BeaconViewModel(application: Application) : AndroidViewModel(application), BeaconConsumer {
    private val beaconManager: BeaconManager = BeaconManager.getInstanceForApplication(application)
    private val _beacons = MutableStateFlow<List<BeaconData>>(emptyList())
    val beacons: StateFlow<List<BeaconData>> = _beacons

    private val _closestBeacon = MutableStateFlow<BeaconData?>(null)
    val closestBeacon: StateFlow<BeaconData?> = _closestBeacon


    init {
        beaconManager.beaconParsers.add(BeaconParser().setBeaconLayout(BeaconParser.ALTBEACON_LAYOUT))
        beaconManager.bind(this)
    }

    override fun onBeaconServiceConnect() {
        beaconManager.addRangeNotifier { beacons, _ ->
            viewModelScope.launch {
                val beaconList = beacons.map { beacon ->
                    BeaconData(
                        uuid = beacon.id1.toString(),
                        major = beacon.id2.toString(),
                        minor = beacon.id3.toString(),
                        distance = beacon.distance,
                        rssi = beacon.rssi
                    )
                }.filter { it.rssi > -70 } // Ignorar beacons con RSSI menor o igual a -70

                _beacons.value = beaconList

                // Seleccionar el beacon con mejor RSSI y menor distancia, si hay al menos uno disponible
                _closestBeacon.value = beaconList
                    .maxWithOrNull(compareByDescending<BeaconData> { it.rssi }
                        .thenBy { it.distance })

                // Si no hay beacons con RSSI > -70, resetear closestBeacon a null
                if (_closestBeacon.value == null) {
                    _closestBeacon.value = null
                }
            }
        }

        try {
            beaconManager.startRangingBeaconsInRegion(Region("all-beacons-region", null, null, null))
        } catch (e: RemoteException) {
            e.printStackTrace()
        }
    }





    override fun getApplicationContext(): Context {
        TODO("Not yet implemented")
    }

    override fun unbindService(connection: ServiceConnection?) {
        TODO("Not yet implemented")
    }

    override fun bindService(intent: Intent?, connection: ServiceConnection?, mode: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun onCleared() {
        super.onCleared()
        beaconManager.unbind(this)
    }

    fun stopScanning() {
        try {
            beaconManager.stopRangingBeaconsInRegion(Region("all-beacons-region", null, null, null))
        } catch (e: RemoteException) {
            e.printStackTrace()
        }
    }

}