package com.example.sistemadedetecciondebeacons.repository


import  android.util.Log
import com.example.sistemadedetecciondebeacons.data.UserUbiData
import com.google.firebase.database.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.*
import java.text.SimpleDateFormat
import java.util.*

class MicroRepository {

    private val url:String = ""
    private val database = FirebaseDatabase.getInstance("https://sdbeacons-default-rtdb.firebaseio.com/")
    private val reference = database.getReference("/")

    private val _microData = MutableStateFlow<List<UserUbiData>>(emptyList())
    val microData: StateFlow<List<UserUbiData>> = _microData

    init {
        fetchMicroData()
    }



    private fun fetchMicroData() {
        reference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val dataList = mutableListOf<UserUbiData>()
                for (childSnapshot in snapshot.children) {
                    val userData = childSnapshot.getValue(UserUbiData::class.java)
                    userData?.let { dataList.add(it) }
                }
                _microData.value = dataList
            }

            override fun onCancelled(error: DatabaseError) {
                _microData.value = listOf()
            }
        })
    }



    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    private var job: Job? = null

    fun startUpdatingUser(userName: String, ultimoBeacon: String, rssi: Int, distancia: Double) {
        coroutineScope.launch {
            Log.d("FIREBASE", "Iniciando actualización de datos...")

            val horaActual = obtenerHoraActual()
            val userData = mapOf(
                "user_Name" to userName,
                "ultimo_Beacon_conectado" to ultimoBeacon,
                "rssi" to rssi,
                "distancia" to distancia,
                "Hora_de_conexion" to horaActual
            )

            reference.child(userName).setValue(userData)
                .addOnSuccessListener {
                    Log.d("FIREBASE", "Datos subidos correctamente")
                }
                .addOnFailureListener {
                    Log.e("FIREBASE", "Error al subir datos: ${it.message}")
                }
        }
    }




    private fun obtenerHoraActual(): String {
        val formato = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
        return formato.format(Date())
    }

    fun stopUpdating() {
        coroutineScope.cancel()
    }

}