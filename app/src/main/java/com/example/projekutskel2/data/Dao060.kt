package com.example.projekutskel2.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao046 {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser_046(user: User046)

    @Query("SELECT * FROM table_user_046 WHERE username_046 = :username LIMIT 1")
    suspend fun getUserByUsername_046(username: String): User046?
}

@Dao
interface NoteDao133 {
    @Query("SELECT * FROM table_note_133 WHERE user_id_046 = :userId ORDER BY tanggal_133 DESC")
    fun getAllNotesByUser_133(userId: Int): Flow<List<Note133>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote_133(note: Note133)

    @Update
    suspend fun updateNote_133(note: Note133)

    @Delete
    suspend fun deleteNote_133(note: Note133)

    @Query("SELECT * FROM table_note_133 WHERE id_133 = :noteId LIMIT 1")
    suspend fun getNoteById_133(noteId: Int): Note133?
}
