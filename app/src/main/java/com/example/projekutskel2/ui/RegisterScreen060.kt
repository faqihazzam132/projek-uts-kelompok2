package com.example.projekutskel2.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.projekutskel2.data.User060
import kotlinx.coroutines.launch

@Composable
fun RegisterScreen060(
    viewModel_060: NoteViewModel060,
    onRegisterSuccess_060: () -> Unit,
    onNavigateToLogin_060: () -> Unit
) {
    var username_060 by remember { mutableStateOf("") }
    var password_060 by remember { mutableStateOf("") }
    var confirmPassword_060 by remember { mutableStateOf("") }
    var error_060 by remember { mutableStateOf("") }
    val scope_060 = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Register Akun 060", fontSize = 24.sp, modifier = Modifier.padding(bottom = 32.dp))

        OutlinedTextField(
            value = username_060,
            onValueChange = { username_060 = it },
            label = { Text("Username") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = password_060,
            onValueChange = { password_060 = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = confirmPassword_060,
            onValueChange = { confirmPassword_060 = it },
            label = { Text("Konfirmasi Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )
        if (error_060.isNotEmpty()) {
            Text(error_060, color = MaterialTheme.colorScheme.error, modifier = Modifier.padding(top = 8.dp))
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                if (password_060 == confirmPassword_060 && username_060.isNotEmpty()) {
                    scope_060.launch {
                        val existing_060 = viewModel_060.login_060(username_060)
                        if (existing_060 == null) {
                            viewModel_060.register_060(User060(username_060 = username_060, password_060 = password_060))
                            onRegisterSuccess_060()
                        } else {
                            error_060 = "Username sudah terdaftar"
                        }
                    }
                } else {
                    error_060 = "Password tidak cocok atau data kosong"
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Register")
        }
        TextButton(onClick = onNavigateToLogin_060) {
            Text("Sudah punya akun? Login")
        }
    }
}
