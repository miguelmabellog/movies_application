package com.example.android.moviesapp.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.android.moviesapp.database.getDatabase
import com.example.android.moviesapp.repository.MoviesRepository
import retrofit2.HttpException

class RefreshData(appContext: Context, params: WorkerParameters):
        CoroutineWorker(appContext, params) {
    companion object {
        const val WORK_NAME = "com.example.android.moviesapp.work.RefreshData"
    }

    override suspend fun doWork(): Result {
        val database = getDatabase(applicationContext)
        val repository = MoviesRepository(database)

        try {
            repository.refreshVideos( )
        } catch (e: HttpException) {
            return Result.retry()
        }

        return Result.success()
    }


}