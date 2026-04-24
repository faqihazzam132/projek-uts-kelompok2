package com.example.projekutskel2.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao060 {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser_060(user: User060)

    @Query("SELECT * FROM table_user_060 WHERE username_060 = :username LIMIT 1")
    suspend fun getUserByUsername_060(username: String): User060?
}

@Dao
interface NoteDao060 {
    @Query("SELECT * FROM table_note_060 WHERE user_id_060 = :userId ORDER BY tanggal_060 DESC")
    fun getAllNotesByUser_060(userId: Int): Flow<List<Note060>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote_060(note: Note060)

    @Update
    suspend fun updateNote_060(note: Note060)

    @Delete
    suspend fun deleteNote_060(note: Note060)

    @Query("SELECT * FROM table_note_060 WHERE id_060 = :noteId LIMIT 1")
    suspend fun getNoteById_060(noteId: Int): Note060?
}
