package com.example.tugas3pamterapan.navigation

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.tugas3pamterapan.data.RegistrationData
import com.example.tugas3pamterapan.ui.RegistrationScreen
import com.example.tugas3pamterapan.ui.SummaryScreen
import com.example.tugas3pamterapan.viewmodel.RegistrationViewModel

object Routes {
    const val REG = "registration"
    const val SUM = "summary"
    const val SUM_ROUTE = "$SUM?nim={nim}&nama={nama}&email={email}"
}

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()
    val vm: RegistrationViewModel = viewModel()

    val uiState by vm.uiState.collectAsStateWithLifecycle()

    NavHost(navController = navController, startDestination = Routes.REG) {
        composable(Routes.REG) {
            RegistrationScreen(
                viewModel = vm,
                uiState = uiState,
            ) { data ->
                val nim = Uri.encode(data.nim)
                val nama = Uri.encode(data.nama)
                val email = Uri.encode(data.email)
                navController.navigate("${Routes.SUM}?nim=$nim&nama=$nama&email=$email")
            }
        }

        composable(
            route = Routes.SUM_ROUTE,
            arguments = listOf(
                navArgument("nim") { type = NavType.StringType; defaultValue = "" },
                navArgument("nama") { type = NavType.StringType; defaultValue = "" },
                navArgument("email") { type = NavType.StringType; defaultValue = "" },
            )
        ) { backStackEntry ->
            val nim = backStackEntry.arguments?.getString("nim").orEmpty()
            val nama = backStackEntry.arguments?.getString("nama").orEmpty()
            val email = backStackEntry.arguments?.getString("email").orEmpty()
            SummaryScreen(RegistrationData(nim, nama, email))
        }
    }
}
