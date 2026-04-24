package com.example.projekutskel2.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.projekutskel2.data.AppDatabase060
import com.example.projekutskel2.data.Note060
import com.example.projekutskel2.data.User060
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class NoteViewModel060(application: Application) : AndroidViewModel(application) {
    private val database_060 = AppDatabase060.getDatabase(application)
    private val userDao_060 = database_060.userDao060()
    private val noteDao_060 = database_060.noteDao060()

    suspend fun register_060(user: User060) {
        userDao_060.insertUser_060(user)
    }

    suspend fun login_060(username: String): User060? {
        return userDao_060.getUserByUsername_060(username)
    }

    fun getNotes_060(userId: Int): Flow<List<Note060>> {
        return noteDao_060.getAllNotesByUser_060(userId)
    }

    fun insertNote_060(note: Note060) {
        viewModelScope.launch {
            noteDao_060.insertNote_060(note)
        }
    }

    fun updateNote_060(note: Note060) {
        viewModelScope.launch {
            noteDao_060.updateNote_060(note)
        }
    }

    fun deleteNote_060(note: Note060) {
        viewModelScope.launch {
            noteDao_060.deleteNote_060(note)
        }
    }
    
    suspend fun getNoteById_060(noteId: Int): Note060? {
        return noteDao_060.getNoteById_060(noteId)
    }
}
