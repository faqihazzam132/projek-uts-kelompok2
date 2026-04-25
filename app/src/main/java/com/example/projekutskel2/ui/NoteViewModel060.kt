package com.example.projekutskel2.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.projekutskel2.data.AppDatabase078
import com.example.projekutskel2.data.Note133
import com.example.projekutskel2.data.User046
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class NoteViewModel039(application: Application) : AndroidViewModel(application) {
    private val database_039 = AppDatabase078.getDatabase(application)
    private val userDao_039 = database_039.userDao046()
    private val noteDao_039 = database_039.noteDao133()

    suspend fun register_039(user: User046) {
        userDao_039.insertUser_046(user)
    }

    suspend fun login_039(username: String): User046? {
        return userDao_039.getUserByUsername_046(username)
    }

    fun getNotes_039(userId: Int): Flow<List<Note133>> {
        return noteDao_039.getAllNotesByUser_133(userId)
    }

    fun insertNote_039(note: Note133) {
        viewModelScope.launch {
            noteDao_039.insertNote_133(note)
        }
    }

    fun updateNote_039(note: Note133) {
        viewModelScope.launch {
            noteDao_039.updateNote_133(note)
        }
    }

    fun deleteNote_039(note: Note133) {
        viewModelScope.launch {
            noteDao_039.deleteNote_133(note)
        }
    }
    
    suspend fun getNoteById_039(noteId: Int): Note133? {
        return noteDao_039.getNoteById_133(noteId)
    }
}
