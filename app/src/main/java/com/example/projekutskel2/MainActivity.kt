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
import com.example.projekutskel2.data.PreferencesManager139
import com.example.projekutskel2.ui.DashboardScreen039
import com.example.projekutskel2.ui.LoginScreen046
import com.example.projekutskel2.ui.NoteFormScreen133
import com.example.projekutskel2.ui.NoteViewModel039
import com.example.projekutskel2.ui.RegisterScreen133
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
    val preferences = remember { PreferencesManager139(context) }
    val viewModel: NoteViewModel039 = viewModel()

    val startDestination = if (preferences.getUserId_139() != -1) "dashboard" else "login"

    NavHost(navController = navController, startDestination = startDestination) {
        composable("login") {
            LoginScreen046(
                viewModel_046 = viewModel,
                onLoginSuccess_046 = { userId, username ->
                    preferences.saveUserSession_139(userId, username)
                    navController.navigate("dashboard") {
                        popUpTo("login") { inclusive = true }
                    }
                },
                onNavigateToRegister_046 = { navController.navigate("register") }
            )
        }
        composable("register") {
            RegisterScreen133(
                viewModel_133 = viewModel,
                onRegisterSuccess_133 = {
                    navController.navigate("login") {
                        popUpTo("register") { inclusive = true }
                    }
                },
                onNavigateToLogin_133 = { navController.navigate("login") }
            )
        }
        composable("dashboard") {
            DashboardScreen039(
                viewModel_039 = viewModel,
                preferences_139 = preferences,
                onAddNote_039 = { navController.navigate("note_form/-1") },
                onEditNote_039 = { noteId -> navController.navigate("note_form/$noteId") },
                onLogout_039 = {
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
            NoteFormScreen133(
                viewModel_133 = viewModel,
                userId_133 = preferences.getUserId_139(),
                noteId_133 = noteId,
                onBack_133 = { navController.popBackStack() }
            )
        }
    }
}
