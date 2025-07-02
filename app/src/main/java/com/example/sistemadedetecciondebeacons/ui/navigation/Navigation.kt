package com.example.sistemadedetecciondebeacons.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.sistemadedetecciondebeacons.ui.screens.RegisterScreen
import com.example.sistemadedetecciondebeacons.ui.screens.LoginScreen
import com.example.sistemadedetecciondebeacons.ui.screens.MicroScreen
import com.example.sistemadedetecciondebeacons.ui.screens.WelcomeScreen
import com.example.sistemadedetecciondebeacons.viewModel.AuthViewModel
import com.example.sistemadedetecciondebeacons.viewModel.RegisterViewModel

@Composable
fun AuthNavigation(navController: NavHostController,
                   innerPadding: Modifier,
                   authViewModel: AuthViewModel,
                   registerViewModel: RegisterViewModel
){
    val id = "Welcome"
    NavHost(navController = navController, startDestination = id) {
        composable("Login") {
            LoginScreen(
                modifier = innerPadding, navController, authViewModel
            )
        }
//
        composable("Register") {
            RegisterScreen(
                navController,
                registerViewModel
            )
        }
        composable("Micro"){
            MicroScreen(modifier = innerPadding, authViewModel = authViewModel)
        }
        composable("Welcome"){
            WelcomeScreen(modifier = innerPadding, navController = navController, authViewModel)
        }

    }

}