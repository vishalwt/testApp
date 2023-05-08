package com.vishal.app.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "jokesTable")
data class Joke(@ColumnInfo(name = "joke")val joke: String){
    @PrimaryKey(autoGenerate = true)
    var id=0
}
