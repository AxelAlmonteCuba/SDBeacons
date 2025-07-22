package com.example.sistemadedetecciondebeacons.data

data class UserUbiData(
    val user_Name: String = "",
    val ultimo_Beacon_conectado: String = "",
    val Hora_de_conexion: String = "",
    val rssi: Int = 0,
    val distancia: Double = 0.0
)
