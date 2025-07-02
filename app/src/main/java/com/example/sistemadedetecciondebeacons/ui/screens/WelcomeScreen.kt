package com.example.sistemadedetecciondebeacons.ui.screens

import  android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import androidx.navigation.NavHostController
import com.example.sistemadedetecciondebeacons.R
import com.example.sistemadedetecciondebeacons.ui.components.ButtonDesign
import com.example.sistemadedetecciondebeacons.ui.components.ButtonDesignLight
import com.example.sistemadedetecciondebeacons.viewModel.AuthViewModel

@Composable
fun WelcomeScreen(modifier: Modifier, navController: NavHostController ,
                  viewModel: AuthViewModel
) {
    val idImage = R.drawable.logo_mantenimientos_app
    val widthImage = integerResource(id = R.integer.width_image)
    val heightImage = integerResource(id = R.integer.height_image)
    val dpSpace = integerResource(id = R.integer.dp_space)
    val userLogged by viewModel.usuario.collectAsState()
    val context = LocalContext.current

    // 🚀 Redirección segura cuando el usuario está logueado
    LaunchedEffect(userLogged) {
        userLogged?.let {

        }
    }
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = idImage),
            contentDescription = null,
            modifier = Modifier.width(widthImage.dp).height(heightImage.dp))
        Spacer(modifier = Modifier.height(dpSpace.dp))
        ButtonDesign("Iniciar Sesion") { navController.navigate("Login")}
        Spacer(modifier = Modifier.height(dpSpace.dp))

        ButtonDesignLight("Registrate") { navController.navigate("Register") }
    }
}