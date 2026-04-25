package com.example.projekutskel2.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

@Composable
fun LoginScreen046(
    viewModel_046: NoteViewModel039,
    onLoginSuccess_046: (Int, String) -> Unit,
    onNavigateToRegister_046: () -> Unit
) {
    var username_046 by remember { mutableStateOf("") }
    var password_046 by remember { mutableStateOf("") }
    var error_046 by remember { mutableStateOf("") }
    val scope_046 = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Login Catatan", fontSize = 24.sp, modifier = Modifier.padding(bottom = 32.dp))

        OutlinedTextField(
            value = username_046,
            onValueChange = { username_046 = it },
            label = { Text("Username") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = password_046,
            onValueChange = { password_046 = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )
        if (error_046.isNotEmpty()) {
            Text(error_046, color = MaterialTheme.colorScheme.error, modifier = Modifier.padding(top = 8.dp))
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                scope_046.launch {
                    val user_046 = viewModel_046.login_039(username_046)
                    if (user_046 != null && user_046.password_046 == password_046) {
                        onLoginSuccess_046(user_046.id_046, user_046.username_046)
                    } else {
                        error_046 = "Username atau Password salah"
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Login")
        }
        TextButton(onClick = onNavigateToRegister_046) {
            Text("Belum punya akun? Register")
        }
    }
}
