package com.example.android.moviesapp.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MoviesDao {
    @Query("select * from databasemovies")
    fun getMovies(): LiveData<List<DatabaseMovies>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll( videos: List<DatabaseMovies>)
}



@Database(entities = [DatabaseMovies::class], version = 1)
abstract class MoviesDatabase: RoomDatabase() {
    abstract val moviesDao: MoviesDao
}

private lateinit var INSTANCE: MoviesDatabase

fun getDatabase(context: Context): MoviesDatabase {
    synchronized(MoviesDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(context.applicationContext,
                    MoviesDatabase::class.java,
                    "movies").build()
        }
    }
    return INSTANCE
}
