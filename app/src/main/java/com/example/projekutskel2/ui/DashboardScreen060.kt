package com.example.projekutskel2.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.projekutskel2.data.Note133
import com.example.projekutskel2.data.PreferencesManager139

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen039(
    viewModel_039: NoteViewModel039,
    preferences_139: PreferencesManager139,
    onAddNote_039: () -> Unit,
    onEditNote_039: (Int) -> Unit,
    onLogout_039: () -> Unit
) {
    val userId_139 = preferences_139.getUserId_139()
    val username_139 = preferences_139.getUsername_139() ?: "User"
    val notes_133 by viewModel_039.getNotes_039(userId_139).collectAsState(initial = emptyList())

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Catatan - $username_139") },
                actions = {
                    IconButton(onClick = {
                        preferences_139.clearSession_139()
                        onLogout_039()
                    }) {
                        Icon(Icons.Default.ExitToApp, contentDescription = "Logout")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onAddNote_039) {
                Icon(Icons.Default.Add, contentDescription = "Tambah Catatan")
            }
        }
    ) { innerPadding_039 ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding_039)
                .padding(8.dp)
        ) {
            items(notes_133) { note_133 ->
                NoteItem039(
                    note_133 = note_133,
                    onEdit_133 = { onEditNote_039(note_133.id_133) },
                    onDelete_133 = { viewModel_039.deleteNote_039(note_133) }
                )
            }
        }
    }
}

@Composable
fun NoteItem039(
    note_133: Note133,
    onEdit_133: () -> Unit,
    onDelete_133: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable { onEdit_133() },
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = note_133.judul_133, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                IconButton(onClick = onDelete_133, modifier = Modifier.size(24.dp)) {
                    Icon(Icons.Default.Delete, contentDescription = "Hapus", tint = MaterialTheme.colorScheme.error)
                }
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = note_133.isi_133, maxLines = 2)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = note_133.tanggal_133, fontSize = 12.sp, color = MaterialTheme.colorScheme.secondary)
        }
    }
}
