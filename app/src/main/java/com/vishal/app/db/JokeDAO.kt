package com.vishal.app.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.vishal.app.Model.Joke

@Dao
interface JokeDAO {
    /**
     * Save Jokes
     */
    @Insert
    fun saveJoke(entities: Joke)

    @Query("SELECT * FROM jokesTable order by id DESC limit 10")
    fun getAllJokes(): List<Joke>

}