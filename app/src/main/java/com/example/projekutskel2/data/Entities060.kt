package com.example.projekutskel2.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(tableName = "table_user_060")
data class User060(
    @PrimaryKey(autoGenerate = true) val id_060: Int = 0,
    val username_060: String,
    val password_060: String
)

@Entity(
    tableName = "table_note_060",
    foreignKeys = [
        ForeignKey(
            entity = User060::class,
            parentColumns = ["id_060"],
            childColumns = ["user_id_060"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["user_id_060"])]
)
data class Note060(
    @PrimaryKey(autoGenerate = true) val id_060: Int = 0,
    val user_id_060: Int,
    val judul_060: String,
    val isi_060: String,
    val tanggal_060: String
)
