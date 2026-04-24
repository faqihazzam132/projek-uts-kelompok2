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
import com.example.projekutskel2.data.Note060
import com.example.projekutskel2.data.PreferencesManager060

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen060(
    viewModel_060: NoteViewModel060,
    preferences_060: PreferencesManager060,
    onAddNote_060: () -> Unit,
    onEditNote_060: (Int) -> Unit,
    onLogout_060: () -> Unit
) {
    val userId_060 = preferences_060.getUserId_060()
    val username_060 = preferences_060.getUsername_060() ?: "User"
    val notes_060 by viewModel_060.getNotes_060(userId_060).collectAsState(initial = emptyList())

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Catatan 060 - $username_060") },
                actions = {
                    IconButton(onClick = {
                        preferences_060.clearSession_060()
                        onLogout_060()
                    }) {
                        Icon(Icons.Default.ExitToApp, contentDescription = "Logout")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onAddNote_060) {
                Icon(Icons.Default.Add, contentDescription = "Tambah Catatan")
            }
        }
    ) { innerPadding_060 ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding_060)
                .padding(8.dp)
        ) {
            items(notes_060) { note_060 ->
                NoteItem060(
                    note_060 = note_060,
                    onEdit_060 = { onEditNote_060(note_060.id_060) },
                    onDelete_060 = { viewModel_060.deleteNote_060(note_060) }
                )
            }
        }
    }
}

@Composable
fun NoteItem060(
    note_060: Note060,
    onEdit_060: () -> Unit,
    onDelete_060: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable { onEdit_060() },
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = note_060.judul_060, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                IconButton(onClick = onDelete_060, modifier = Modifier.size(24.dp)) {
                    Icon(Icons.Default.Delete, contentDescription = "Hapus", tint = MaterialTheme.colorScheme.error)
                }
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = note_060.isi_060, maxLines = 2)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = note_060.tanggal_060, fontSize = 12.sp, color = MaterialTheme.colorScheme.secondary)
        }
    }
}
