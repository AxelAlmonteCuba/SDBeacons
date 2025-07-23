package com.example.sistemadedetecciondebeacons.ui.screens

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.sistemadedetecciondebeacons.AuthActivity
import com.example.sistemadedetecciondebeacons.MainActivity
import com.example.sistemadedetecciondebeacons.R
import com.example.sistemadedetecciondebeacons.ui.components.BasicTopAppBar
import com.example.sistemadedetecciondebeacons.ui.components.OptionConfig
import com.example.sistemadedetecciondebeacons.viewModel.AuthViewModel

@Composable
fun ConfigScreen(modifier: Modifier, viewModel: AuthViewModel, navController: NavController) {
    val space_dp = integerResource(id = R.integer.space_Config).dp
//
//    val notify_Icon = R.drawable.notifiys
//    val password_icon = R.drawable.password
//    val deleteAccount_icon = R.drawable.eliminar_cuenta
//    val optionAdmin_icon = R.drawable.users
    val logout_icon = R.drawable.logout_2_svgrepo_com



    val context = LocalContext.current

    val dpPaddingStart = integerResource(id = R.integer.paddingListsStart)
    val dpPaddingEnd = integerResource(id = R.integer.paddingListsEnd)

    var showDialog by remember { mutableStateOf(false) }

    var userRol by remember { mutableStateOf<String?>(null) }
    Log.d("AppActivity", "Rol recuperado: $userRol")

    var rol by remember { mutableStateOf("") }



    LaunchedEffect(userRol) {
        val prefs = context.getSharedPreferences("bluxi_prefs", Context.MODE_PRIVATE)
        userRol = prefs.getString("ROL", null)
        rol = userRol.orEmpty()
        Log.d("AppActivity", "Nombre recuperado: $rol")
    }


    Scaffold(
        topBar = {
            BasicTopAppBar(
                title = "Configuracion",
                navController = navController,
                modifier = Modifier
            )
        }
    ) { innerPadding ->

        Column(
            modifier = modifier
                .padding(innerPadding)
                .padding(start = dpPaddingStart.dp, end = dpPaddingEnd.dp)
        ) {
//            OptionConfig(notify_Icon, "Notificaciones") {
//
//            }
////            Spacer(modifier = Modifier.height(space_dp))
//            OptionConfig(password_icon, "Cambiar contraseña") { }
////            Spacer(modifier = Modifier.height(space_dp))
//            OptionConfig(deleteAccount_icon, "Elimnar Cuenta") { }
////            Spacer(modifier = Modifier.height(space_dp))
//            if (rol.equals("admin")) {
//                OptionConfig(optionAdmin_icon, "Opciones de Administrador") {
//                    navController.navigate(
//                        Routes.OptionConfigAdmin.route
//                    )
//                }
//            }
//            Spacer(modifier = Modifier.height(space_dp))
            OptionConfig(logout_icon, "Cerrar Sesion") {

                viewModel.cerrarSesion()
                val intent = Intent(context, AuthActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                }
                context.startActivity(intent)
            }

        }

    }
}