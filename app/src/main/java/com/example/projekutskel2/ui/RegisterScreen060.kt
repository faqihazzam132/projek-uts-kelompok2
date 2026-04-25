package com.example.projekutskel2.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.projekutskel2.data.User046
import kotlinx.coroutines.launch

@Composable
fun RegisterScreen133(
    viewModel_133: NoteViewModel039,
    onRegisterSuccess_133: () -> Unit,
    onNavigateToLogin_133: () -> Unit
) {
    var username_133 by remember { mutableStateOf("") }
    var password_133 by remember { mutableStateOf("") }
    var confirmPassword_133 by remember { mutableStateOf("") }
    var error_133 by remember { mutableStateOf("") }
    val scope_133 = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Register Akun", fontSize = 24.sp, modifier = Modifier.padding(bottom = 32.dp))

        OutlinedTextField(
            value = username_133,
            onValueChange = { username_133 = it },
            label = { Text("Username") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = password_133,
            onValueChange = { password_133 = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = confirmPassword_133,
            onValueChange = { confirmPassword_133 = it },
            label = { Text("Konfirmasi Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )
        if (error_133.isNotEmpty()) {
            Text(error_133, color = MaterialTheme.colorScheme.error, modifier = Modifier.padding(top = 8.dp))
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                if (password_133 == confirmPassword_133 && username_133.isNotEmpty()) {
                    scope_133.launch {
                        val existing_133 = viewModel_133.login_039(username_133)
                        if (existing_133 == null) {
                            viewModel_133.register_039(User046(username_046 = username_133, password_046 = password_133))
                            onRegisterSuccess_133()
                        } else {
                            error_133 = "Username sudah terdaftar"
                        }
                    }
                } else {
                    error_133 = "Password tidak cocok atau data kosong"
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Register")
        }
        TextButton(onClick = onNavigateToLogin_133) {
            Text("Sudah punya akun? Login")
        }
    }
}
