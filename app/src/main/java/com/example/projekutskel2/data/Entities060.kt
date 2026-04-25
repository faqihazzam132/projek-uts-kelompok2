package com.example.projekutskel2.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(tableName = "table_user_046")
data class User046(
    @PrimaryKey(autoGenerate = true) val id_046: Int = 0,
    val username_046: String,
    val password_046: String
)

@Entity(
    tableName = "table_note_133",
    foreignKeys = [
        ForeignKey(
            entity = User046::class,
            parentColumns = ["id_046"],
            childColumns = ["user_id_046"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["user_id_046"])]
)
data class Note133(
    @PrimaryKey(autoGenerate = true) val id_133: Int = 0,
    val user_id_046: Int,
    val judul_133: String,
    val isi_133: String,
    val tanggal_133: String
)
