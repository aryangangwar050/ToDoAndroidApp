package com.example.notes

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Note::class), version = 1, exportSchema = false)
abstract class NoteDatabase:RoomDatabase() {
    abstract fun getNoteDao(): NoteDao

    companion object{

        @Volatile
        private var INSTANCE :NoteDatabase?= null

        fun getDatabase(context:Context):NoteDatabase{
            //If instance is not null ,than return it,
            // if it is, then create the database...
            return (INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NoteDatabase::class.java,
                    "note_DataBase").build()
                INSTANCE = instance

                // return instance
                instance

            }) as NoteDatabase
        }

    }
}