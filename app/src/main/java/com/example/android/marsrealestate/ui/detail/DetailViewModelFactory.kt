package com.example.android.marsrealestate.ui.detail

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.marsrealestate.domain.ModelMovie
import com.example.android.marsrealestate.network.MovieProperty


class DetailViewModelFactory(
        private val movie: ModelMovie,
        private val application: Application) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(movie, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
