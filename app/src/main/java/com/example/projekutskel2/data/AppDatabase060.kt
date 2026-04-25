package com.example.projekutskel2.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User046::class, Note133::class], version = 1, exportSchema = false)
abstract class AppDatabase078 : RoomDatabase() {
    abstract fun userDao046(): UserDao046
    abstract fun noteDao133(): NoteDao133

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase078? = null

        fun getDatabase(context: Context): AppDatabase078 {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase078::class.java,
                    "database_078"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
