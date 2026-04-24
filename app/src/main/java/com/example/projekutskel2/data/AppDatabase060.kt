package com.example.projekutskel2.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User060::class, Note060::class], version = 1, exportSchema = false)
abstract class AppDatabase060 : RoomDatabase() {
    abstract fun userDao060(): UserDao060
    abstract fun noteDao060(): NoteDao060

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase060? = null

        fun getDatabase(context: Context): AppDatabase060 {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase060::class.java,
                    "database_060"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
