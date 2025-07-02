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

data class BeaconData(
    val uuid: String,
    val major: String,
    val minor: String,
    val distance: Double,
    val rssi: Int // <-- Agregamos el campo para el RSSI
)