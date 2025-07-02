package com.example.sistemadedetecciondebeacons

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.example.sistemadedetecciondebeacons.repository.AuthRepository
import com.example.sistemadedetecciondebeacons.ui.navigation.AuthNavigation
import com.example.sistemadedetecciondebeacons.viewModel.AuthViewModel
import com.example.sistemadedetecciondebeacons.viewModel.AuthViewModelFactory
import com.example.sistemadedetecciondebeacons.viewModel.RegisterViewModel


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Data
        val authRepository = AuthRepository()
        val authViewModel = ViewModelProvider(
            this,
            AuthViewModelFactory(authRepository)
        ).get(AuthViewModel::class.java)
        val registerViewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)

        //Data

        setContent {
            val navController = rememberNavController()


            MaterialTheme {
                Scaffold(

                ) { innerPadding ->
                    AuthNavigation(
                        navController = navController,
                        innerPadding = Modifier.padding(innerPadding),
                        authViewModel = authViewModel,
                        registerViewModel = registerViewModel
                    )
                }

            }

        }
    }





    //   beaconManager.unbind(this)

}
