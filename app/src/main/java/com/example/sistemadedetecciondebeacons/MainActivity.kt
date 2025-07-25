package com.example.sistemadedetecciondebeacons

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.example.sistemadedetecciondebeacons.beaconConfig.BeaconService
import com.example.sistemadedetecciondebeacons.repository.AuthRepository
import com.example.sistemadedetecciondebeacons.ui.navigation.AppNavigation
import com.example.sistemadedetecciondebeacons.ui.navigation.AuthNavigation
import com.example.sistemadedetecciondebeacons.viewModel.AuthViewModel
import com.example.sistemadedetecciondebeacons.viewModel.AuthViewModelFactory
import com.example.sistemadedetecciondebeacons.viewModel.RegisterViewModel


class MainActivity : ComponentActivity() {

    private val PRE_ANDROID_12_PERMISSIONS = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.FOREGROUND_SERVICE
    )

    // Permisos para Android 12+
    private val ANDROID_12_PERMISSIONS = arrayOf(
        Manifest.permission.BLUETOOTH_SCAN,
        Manifest.permission.BLUETOOTH_CONNECT,
        Manifest.permission.ACCESS_FINE_LOCATION
    )

    private val LOCATION_PERMISSION_REQUEST_CODE = 123
    private val BLUETOOTH_PERMISSION_REQUEST_CODE = 124


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val authRepository = AuthRepository()
        val authViewModel = ViewModelProvider(
            this,
            AuthViewModelFactory(authRepository)
        ).get(AuthViewModel::class.java)

        val beaconService =  BeaconService()
        beaconService.VerificarPremisos(this, this)

        setContent {
            val navController = rememberNavController()


            MaterialTheme {
                Scaffold(

                ) { innerPadding ->
                    AppNavigation(
                        navController = navController,
                        innerPadding = Modifier.padding(innerPadding),
                        authViewModel = authViewModel
                    )
                }

            }
        }
    }

}
