package com.example.sistemadedetecciondebeacons.viewModel

import  androidx.lifecycle.ViewModel
import com.example.sistemadedetecciondebeacons.data.UserUbiData
import com.example.sistemadedetecciondebeacons.repository.MicroRepository
import kotlinx.coroutines.flow.StateFlow

class MicroViewModel : ViewModel() {
    private val repository = MicroRepository()
    val microData: StateFlow<List<UserUbiData>> = repository.microData

    fun startUpdatingUser(userName: String, ultimoBeacon: String, rssi: Int, distance: Double) {
        repository.startUpdatingUser(userName, ultimoBeacon, rssi, distance)
    }

    override fun onCleared() {
        super.onCleared()
        repository.stopUpdating()
    }
}