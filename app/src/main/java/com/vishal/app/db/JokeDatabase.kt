package com.vishal.app.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.vishal.app.Model.Joke


@Database(entities = [Joke::class], version = 1)
abstract class JokeDatabase : RoomDatabase() {
    abstract fun jokeDao(): JokeDAO

    companion object {
        @Volatile
        private var instance: JokeDatabase? = null

        fun getInstance(context: Context): JokeDatabase {
            if (instance == null) {
                synchronized(this) {
                    instance = Room.databaseBuilder(context.applicationContext, JokeDatabase::class.java, "joke_database").build()
                }
            }
            return instance!!
        }
    }
}