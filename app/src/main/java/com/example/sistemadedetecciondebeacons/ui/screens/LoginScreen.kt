package com.example.sistemadedetecciondebeacons.ui.screens

import  android.content.Context
import android.content.Intent
import android.util.Log
import androidx.compose.foundation.layout.Arrangement

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import androidx.navigation.NavHostController
import com.example.sistemadedetecciondebeacons.R
import com.example.sistemadedetecciondebeacons.ui.components.BasicTopAppBar
import com.example.sistemadedetecciondebeacons.ui.components.ButtonDesign
import com.example.sistemadedetecciondebeacons.ui.components.ButtonSocialMedia
import com.example.sistemadedetecciondebeacons.ui.components.PasswordTextField
import com.example.sistemadedetecciondebeacons.ui.components.TextFieldBasic
import com.example.sistemadedetecciondebeacons.ui.components.TextForms
import com.example.sistemadedetecciondebeacons.ui.components.TextFormsInfo
import com.example.sistemadedetecciondebeacons.viewModel.AuthViewModel

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier, // Agregar un valor por defecto para evitar errores
    navController: NavHostController,
    viewModel: AuthViewModel
) {
    val userLogged by viewModel.usuario.collectAsState()
    val error by viewModel.error.collectAsState()

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val context = LocalContext.current // Para mostrar mensajes
    val dpSpac = integerResource(id = R.integer.dp_space).dp // Convertir directamente





    LaunchedEffect(userLogged) {
        userLogged?.let {
            // Guardar en SharedPreferences
            val user_Name = userLogged!!.nombre
            val user_rol = userLogged!!.rol
            Log.d("LoginScreen", "Guardando nombre: $user_Name")
            val prefs = context.getSharedPreferences("bluxi_prefs", Context.MODE_PRIVATE)
            prefs.edit().putString("USER_NAME", user_Name).apply()
            prefs.edit().putString("USER_ROL", user_rol).apply()
            navController.navigate("Micro")

        }
    }

    BasicTopAppBar("Iniciar Sesión", modifier = Modifier, navController = navController)

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(30.dp),
        verticalArrangement = Arrangement.Center
    ) {
        TextForms(text = "Correo Electrónico o Teléfono")
        Spacer(modifier = Modifier.height(dpSpac))

        TextFieldBasic(value = email, content = "correo@correo") { email = it }
        Spacer(modifier = Modifier.height(2 * dpSpac))

        TextForms(text = "Contraseña")
        Spacer(modifier = Modifier.height(dpSpac))

        PasswordTextField(value = password, content = "contraseña") { password = it }
        Spacer(modifier = Modifier.height(4 * dpSpac))

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ButtonDesign("Iniciar Sesión") {
                if (email.isBlank() || password.isBlank()) {
                    viewModel.setError("Todos los campos son obligatorios")
                } else {
                    viewModel.iniciarSesion(email, password)
                }
            }


        }
    }
}
