package com.example.imc

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ImcRegistro::class], version = 1)
abstract class ImcDatabase : RoomDatabase() {
    abstract fun imcDao(): ImcDao

    companion object {
        @Volatile
        private var INSTANCE: ImcDatabase? = null

        fun getDatabase(context: Context): ImcDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ImcDatabase::class.java,
                    "imc_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
