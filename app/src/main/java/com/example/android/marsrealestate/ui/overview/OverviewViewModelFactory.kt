package com.example.android.marsrealestate.ui.overview

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.marsrealestate.network.MovieProperty
import com.example.android.marsrealestate.repository.MoviesRepository
import com.example.android.marsrealestate.ui.detail.DetailViewModel

class OverviewViewModelFactory(val app: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(OverviewViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return OverviewViewModel(app) as T
        }
        throw IllegalArgumentException("Unable to construct viewmodel")
    }
}

}