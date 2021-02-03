package com.example.android.marsrealestate.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.android.marsrealestate.domain.ModelMovie

@Entity
data class DatabaseMovies constructor(
        @PrimaryKey
        val id: Int,
        val description: String?,
        val image: String,
        val name: String)


fun List<DatabaseMovies>.asDomainModel(): List<ModelMovie> {
    return map {
        ModelMovie(
                id = it.id,
                description = it.description,
                image = it.image,
                name = it.name)
    }
}