package com.example.projekutskel2.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.projekutskel2.data.Note133
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteFormScreen133(
    viewModel_133: NoteViewModel039,
    userId_133: Int,
    noteId_133: Int?,
    onBack_133: () -> Unit
) {
    var judul_133 by remember { mutableStateOf("") }
    var isi_133 by remember { mutableStateOf("") }
    val scope_133 = rememberCoroutineScope()
    
    LaunchedEffect(noteId_133) {
        if (noteId_133 != null && noteId_133 != -1) {
            val note_133 = viewModel_133.getNoteById_039(noteId_133)
            if (note_133 != null) {
                judul_133 = note_133.judul_133
                isi_133 = note_133.isi_133
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(if (noteId_133 == null || noteId_133 == -1) "Tambah Catatan" else "Edit Catatan") },
                navigationIcon = {
                    IconButton(onClick = onBack_133) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Kembali")
                    }
                }
            )
        }
    ) { innerPadding_133 ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding_133)
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = judul_133,
                onValueChange = { judul_133 = it },
                label = { Text("Judul") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = isi_133,
                onValueChange = { isi_133 = it },
                label = { Text("Isi Catatan") },
                modifier = Modifier.fillMaxWidth().weight(1f)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    if (judul_133.isNotEmpty() && isi_133.isNotEmpty()) {
                        val date_133 = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault()).format(Date())
                        if (noteId_133 == null || noteId_133 == -1) {
                            viewModel_133.insertNote_039(
                                Note133(
                                    user_id_046 = userId_133,
                                    judul_133 = judul_133,
                                    isi_133 = isi_133,
                                    tanggal_133 = date_133
                                )
                            )
                        } else {
                            viewModel_133.updateNote_039(
                                Note133(
                                    id_133 = noteId_133,
                                    user_id_046 = userId_133,
                                    judul_133 = judul_133,
                                    isi_133 = isi_133,
                                    tanggal_133 = date_133
                                )
                            )
                        }
                        onBack_133()
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Simpan")
            }
        }
    }
}
