package com.example.android.marsrealestate.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.android.marsrealestate.database.MoviesDatabase
import com.example.android.marsrealestate.database.asDomainModel
import com.example.android.marsrealestate.domain.ModelMovie
import com.example.android.marsrealestate.network.MoviesApi
import com.example.android.marsrealestate.network.MoviesApiService
import com.example.android.marsrealestate.network.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MoviesRepository (private val database: MoviesDatabase) {

    val movies: LiveData<List<ModelMovie>> = Transformations.map(database.moviesDao.getMovies()) {
        it.asDomainModel()
    }

    suspend fun refreshVideos() {
        withContext(Dispatchers.IO) {
            val movieslist = MoviesApi.RETROFIT_SERVICE.getProperties()
            database.moviesDao.insertAll(movieslist.asDatabaseModel())
        }
    }

}
