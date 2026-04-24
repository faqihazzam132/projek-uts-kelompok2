package com.example.projekutskel2.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.projekutskel2.data.Note060
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteFormScreen060(
    viewModel_060: NoteViewModel060,
    userId_060: Int,
    noteId_060: Int?,
    onBack_060: () -> Unit
) {
    var judul_060 by remember { mutableStateOf("") }
    var isi_060 by remember { mutableStateOf("") }
    val scope_060 = rememberCoroutineScope()
    
    LaunchedEffect(noteId_060) {
        if (noteId_060 != null && noteId_060 != -1) {
            val note_060 = viewModel_060.getNoteById_060(noteId_060)
            if (note_060 != null) {
                judul_060 = note_060.judul_060
                isi_060 = note_060.isi_060
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(if (noteId_060 == null || noteId_060 == -1) "Tambah Catatan 060" else "Edit Catatan 060") },
                navigationIcon = {
                    IconButton(onClick = onBack_060) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Kembali")
                    }
                }
            )
        }
    ) { innerPadding_060 ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding_060)
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = judul_060,
                onValueChange = { judul_060 = it },
                label = { Text("Judul") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = isi_060,
                onValueChange = { isi_060 = it },
                label = { Text("Isi Catatan") },
                modifier = Modifier.fillMaxWidth().weight(1f)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    if (judul_060.isNotEmpty() && isi_060.isNotEmpty()) {
                        val date_060 = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault()).format(Date())
                        if (noteId_060 == null || noteId_060 == -1) {
                            viewModel_060.insertNote_060(
                                Note060(
                                    user_id_060 = userId_060,
                                    judul_060 = judul_060,
                                    isi_060 = isi_060,
                                    tanggal_060 = date_060
                                )
                            )
                        } else {
                            viewModel_060.updateNote_060(
                                Note060(
                                    id_060 = noteId_060,
                                    user_id_060 = userId_060,
                                    judul_060 = judul_060,
                                    isi_060 = isi_060,
                                    tanggal_060 = date_060
                                )
                            )
                        }
                        onBack_060()
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Simpan")
            }
        }
    }
}
