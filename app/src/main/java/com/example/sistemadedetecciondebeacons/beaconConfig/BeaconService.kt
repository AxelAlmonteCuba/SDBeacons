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

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.content.pm.PackageManager
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import org.altbeacon.beacon.BeaconConsumer
import org.altbeacon.beacon.BeaconManager
import org.altbeacon.beacon.BeaconParser
import org.altbeacon.beacon.Region

class BeaconService: BeaconConsumer {
    private lateinit var beaconManager: BeaconManager
    private val region = Region("all-beacons", null, null, null)

    fun VerificarPremisos(activity: Activity, context: Context) {


        // Verificar permisos
        checkPermissions(activity)

        // Inicializar BeaconManager
        beaconManager = BeaconManager.getInstanceForApplication(context)
        beaconManager.beaconParsers.add(BeaconParser().setBeaconLayout("m:2-3=0215,i:4-19,i:20-21,i:22-23,p:24-24"))

        // Conectar el servicio de beacons
        beaconManager.bind(this)


    }

    override fun onBeaconServiceConnect() {
        beaconManager.addRangeNotifier { beacons, _ ->
            if (beacons.isNotEmpty()) {
                for (beacon in beacons) {
                    Log.d("Beacon Detectado", "UUID: ${beacon.id1}, Major: ${beacon.id2}, Minor: ${beacon.id3}, Distancia: ${beacon.distance}m")
                }
            } else {
                Log.d("Beacon", "No se detectaron beacons")
            }
        }
        beaconManager.startRangingBeacons(region)
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

    private fun checkPermissions(activity : Activity) {
        val permissions = arrayOf(
            Manifest.permission.BLUETOOTH_SCAN,
            Manifest.permission.BLUETOOTH_CONNECT,
            Manifest.permission.ACCESS_FINE_LOCATION
        )

        if (permissions.any { ContextCompat.checkSelfPermission(activity, it) != PackageManager.PERMISSION_GRANTED }) {
            ActivityCompat.requestPermissions(activity, permissions, 1)
        }
    }

}