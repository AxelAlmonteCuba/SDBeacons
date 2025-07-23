package com.example.sistemadedetecciondebeacons.ui.screens

import android.content.Context
import  android.util.Log
import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.sistemadedetecciondebeacons.R
import com.example.sistemadedetecciondebeacons.beaconConfig.BeaconDataProvider
import com.example.sistemadedetecciondebeacons.data.UserUbiData
import com.example.sistemadedetecciondebeacons.beaconConfig.BeaconService
import com.example.sistemadedetecciondebeacons.beaconConfig.BeaconViewModel
import com.example.sistemadedetecciondebeacons.ui.components.BasicTopAppBar
import com.example.sistemadedetecciondebeacons.ui.components.BeaconConnection
import com.example.sistemadedetecciondebeacons.ui.components.CardLocation
import com.example.sistemadedetecciondebeacons.ui.components.CardUser
import com.example.sistemadedetecciondebeacons.ui.components.HomeTopAppBar
import com.example.sistemadedetecciondebeacons.viewModel.AuthViewModel
import com.example.sistemadedetecciondebeacons.viewModel.MicroViewModel


@Composable
fun MicroScreen(viewModel: MicroViewModel = viewModel(), modifier: Modifier, authViewModel: AuthViewModel, navController: NavController) {
    val microData by viewModel.microData.collectAsState()

    val userData = remember { mutableStateOf<Map<String, Any>?>(null) }
    val user_Name = userData.value?.get("nombre").toString()

    val icon_user = R.drawable.user

    val color_box = colorResource(id = R.color.IconColor)

    val rouder_shape = integerResource(R.integer.rounder_shape_home)


    var userName by remember { mutableStateOf<String?>(null) }
    var userRol by remember { mutableStateOf<String?>(null) }
    Log.d("AppActivity", "Nombre recuperado: $userName")

    var names by remember { mutableStateOf("") }

    val context = LocalContext.current
    val closestBeacon by BeaconDataProvider.closestBeacon.collectAsState()
    val isAdmin = userRol.equals("admin")

    LaunchedEffect(userName) {
        val prefs = context.getSharedPreferences("bluxi_prefs", Context.MODE_PRIVATE)
        userName = prefs.getString("USER_NAME", null)
        userRol = prefs.getString("USER_ROL", null)
        names = userName.orEmpty()
        Log.d("AppActivity", "Nombre recuperado: $userName")
    }

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


    Scaffold(topBar = { HomeTopAppBar(icon_user, user_Name, modifier){
        navController.navigate("Config")
    } }) { innerPadding ->
            Column(
                modifier = modifier.fillMaxSize().padding(innerPadding).padding(start = 15.dp, end = 15.dp),
                verticalArrangement = if (!isAdmin) Arrangement.Center else Arrangement.Top
            ) {


                if (isAdmin) {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(16.dp)
                    ) {
                        items(microData) { user ->
                            Log.d("DEBUG", "Distancia de ${user.user_Name}: ${user.distancia}")
                            CardUser(user.user_Name, user.ultimo_Beacon_conectado,user.Hora_de_conexion, user.distancia)
                        }
                    }
                } else {
                    Box(
                        modifier = Modifier
                            .width(335.dp)
                            .height(72.dp)
                            .clip(RoundedCornerShape(rouder_shape.dp))
                            .background(color_box),
                        contentAlignment = Alignment.Center
                    ) {
                        closestBeacon?.let { beacon ->
                            CardLocation(location = beacon.minor) {
                                // navController.navigate(Routes.Micro.route)
                            }

                        } ?: CardLocation(location = "Buscando...") {

                        }

                    }

                }
                BeaconConnection(user_Name = user_Name)

            }

        }
    }
