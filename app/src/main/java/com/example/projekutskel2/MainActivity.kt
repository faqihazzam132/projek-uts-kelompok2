package com.example.projekutskel2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.projekutskel2.data.PreferencesManager060
import com.example.projekutskel2.ui.DashboardScreen060
import com.example.projekutskel2.ui.LoginScreen060
import com.example.projekutskel2.ui.NoteFormScreen060
import com.example.projekutskel2.ui.NoteViewModel060
import com.example.projekutskel2.ui.RegisterScreen060
import com.example.projekutskel2.ui.theme.ProjekUTSKel2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProjekUTSKel2Theme {
                AppNavigation()
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val context = androidx.compose.ui.platform.LocalContext.current
    val preferences = remember { PreferencesManager060(context) }
    val viewModel: NoteViewModel060 = viewModel()

    val startDestination = if (preferences.getUserId_060() != -1) "dashboard" else "login"

    NavHost(navController = navController, startDestination = startDestination) {
        composable("login") {
            LoginScreen060(
                viewModel_060 = viewModel,
                onLoginSuccess_060 = { userId, username ->
                    preferences.saveUserSession_060(userId, username)
                    navController.navigate("dashboard") {
                        popUpTo("login") { inclusive = true }
                    }
                },
                onNavigateToRegister_060 = { navController.navigate("register") }
            )
        }
        composable("register") {
            RegisterScreen060(
                viewModel_060 = viewModel,
                onRegisterSuccess_060 = {
                    navController.navigate("login") {
                        popUpTo("register") { inclusive = true }
                    }
                },
                onNavigateToLogin_060 = { navController.navigate("login") }
            )
        }
        composable("dashboard") {
            DashboardScreen060(
                viewModel_060 = viewModel,
                preferences_060 = preferences,
                onAddNote_060 = { navController.navigate("note_form/-1") },
                onEditNote_060 = { noteId -> navController.navigate("note_form/$noteId") },
                onLogout_060 = {
                    navController.navigate("login") {
                        popUpTo("dashboard") { inclusive = true }
                    }
                }
            )
        }
        composable(
            "note_form/{noteId}",
            arguments = listOf(navArgument("noteId") { type = NavType.IntType })
        ) { backStackEntry ->
            val noteId = backStackEntry.arguments?.getInt("noteId") ?: -1
            NoteFormScreen060(
                viewModel_060 = viewModel,
                userId_060 = preferences.getUserId_060(),
                noteId_060 = noteId,
                onBack_060 = { navController.popBackStack() }
            )
        }
    }
}
