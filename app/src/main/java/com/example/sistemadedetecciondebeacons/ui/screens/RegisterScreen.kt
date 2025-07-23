package com.example.sistemadedetecciondebeacons.ui.screens

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
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
import androidx.navigation.NavController
import com.example.sistemadedetecciondebeacons.MainActivity

import com.example.sistemadedetecciondebeacons.R

import com.example.sistemadedetecciondebeacons.ui.components.*
import com.example.sistemadedetecciondebeacons.viewModel.RegisterViewModel

@Composable
fun RegisterScreen(navController: NavController, viewModel: RegisterViewModel) {
    var names by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var dateBirth by remember { mutableStateOf("") }

    val context = LocalContext.current

    val dpSpac = integerResource(id = R.integer.dp_space)

    Scaffold(
        topBar = {
            BasicTopAppBar (
                title = "Registrarse",
                navController = navController,
                modifier = Modifier
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding) // Asegura que no se sobreponga con la barra de navegación
                .padding(horizontal = 30.dp)
                .verticalScroll(rememberScrollState())
        ) {
            TextForms("Nombre Completo")
            Spacer(Modifier.height(dpSpac.dp))
            TextFieldBasic(value = names, "Nombres y Apellidos") { names = it }
            Spacer(Modifier.height(2 * dpSpac.dp))

            TextForms("Correo")
            Spacer(Modifier.height(dpSpac.dp))
            TextFieldBasic(value = email, "correo@correo") { email = it }
            Spacer(Modifier.height(2 * dpSpac.dp))

            TextForms("Contraseña")
            Spacer(Modifier.height(dpSpac.dp))
            PasswordTextField(value = password, "contraseña") { password = it }
            Spacer(Modifier.height(2 * dpSpac.dp))

            TextForms("Número Telefónico")
            Spacer(Modifier.height(dpSpac.dp))
            TextFieldNumber(value = phoneNumber, "999999999") { phoneNumber = it }
            Spacer(Modifier.height(2 * dpSpac.dp))

            TextForms("Fecha de nacimiento")
            FieldDate(fechaSeleccionada = dateBirth) { dateBirth = it }
            Spacer(Modifier.height(2 * dpSpac.dp))

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ButtonDesign("Registrarse") {
                    viewModel.registrarUsuario(names, email, password, phoneNumber, dateBirth) { exito, mensaje ->
                        if (exito) {
                            val prefs = context.getSharedPreferences("bluxi_prefs", Context.MODE_PRIVATE)
                            prefs.edit().putString("USER_NAME", names).apply()
                            prefs.edit().putString("USER_ROL", "user").apply()

                            // Lanzar el Activity
                            val intent = Intent(context, MainActivity::class.java).apply {
                                putExtra("USER_NAME", names)
                                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            }
                            context.startActivity(intent)

                        } else {
                            Log.e("Registro", "Error: $mensaje")
                        }
                    }
                }

            }
        }
    }
}
