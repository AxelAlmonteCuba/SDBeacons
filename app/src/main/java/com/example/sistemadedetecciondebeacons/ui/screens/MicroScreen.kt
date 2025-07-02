package com.example.sistemadedetecciondebeacons.ui.screens

import  android.util.Log
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sistemadedetecciondebeacons.R
import com.example.sistemadedetecciondebeacons.data.UserUbiData
import com.example.sistemadedetecciondebeacons.beaconConfig.BeaconService
import com.example.sistemadedetecciondebeacons.beaconConfig.BeaconViewModel
import com.example.sistemadedetecciondebeacons.ui.components.BasicTopAppBar
import com.example.sistemadedetecciondebeacons.ui.components.BeaconConnection
import com.example.sistemadedetecciondebeacons.ui.components.HomeTopAppBar
import com.example.sistemadedetecciondebeacons.viewModel.AuthViewModel
import com.example.sistemadedetecciondebeacons.viewModel.MicroViewModel


@Composable
fun MicroScreen(viewModel: MicroViewModel = viewModel(), modifier: Modifier, authViewModel: AuthViewModel) {
    val microData by viewModel.microData.collectAsState()

    val userData = remember { mutableStateOf<Map<String, Any>?>(null) }
    val user_Name = userData.value?.get("nombre").toString()

    val icon_user = R.drawable.user


    LaunchedEffect(Unit) {
        authViewModel.getUserData { data, error ->
            if (data != null) {
                userData.value = data
            } else {
                Log.e("HomeScreen", "Error: $error")
            }
        }


    }

    Log.d("MicroScreen", "Usuario: $user_Name")


    Column(modifier = modifier.fillMaxSize().padding(start = 15.dp, end = 15.dp)) {
        HomeTopAppBar(icon_user, user_Name)

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(microData) { user ->
                UserCard(user)
            }
        }
        BeaconConnection(user_Name = user_Name)
    }

}

@Composable
fun UserCard(user: UserUbiData) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Usuario: ${user.user_Name}", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Text(text = "Último Beacon: ${user.ultimo_Beacon_conectado}", fontSize = 16.sp)
            Text(text = "Hora de Conexión: ${user.Hora_de_conexion}", fontSize = 16.sp)
            Text(text = "Rssi: ${user.rssi}", fontSize = 16.sp)
            Text(text = "Distance: ${user.distance}", fontSize = 16.sp)
        }
    }
}
