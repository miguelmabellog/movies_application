package com.example.android.marsrealestate.ui.detail

import android.app.Application
import androidx.lifecycle.*
import com.example.android.marsrealestate.domain.ModelMovie
import com.example.android.marsrealestate.network.NetworkMoviesContainer
import com.example.android.marsrealestate.network.MovieProperty

class DetailViewModel(movie: ModelMovie,
                      app: Application) : AndroidViewModel(app) {
    private val _selectedProperty = MutableLiveData<ModelMovie>()

    val selectedProperty: LiveData<ModelMovie>
        get() = _selectedProperty

    init {
        _selectedProperty.value = movie
    }



}

