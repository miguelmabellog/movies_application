package com.example.android.moviesapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.android.moviesapp.database.MoviesDatabase
import com.example.android.moviesapp.database.asDomainModel
import com.example.android.moviesapp.domain.ModelMovie
import com.example.android.moviesapp.network.MoviesApi
import com.example.android.moviesapp.network.asDatabaseModel
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
