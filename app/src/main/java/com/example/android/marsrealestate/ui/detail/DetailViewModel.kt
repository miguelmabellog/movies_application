package com.example.android.marsrealestate.ui.detail

import android.app.Application
import androidx.lifecycle.*
import com.example.android.marsrealestate.network.NetworkMoviesContainer
import com.example.android.marsrealestate.network.MovieProperty

class DetailViewModel(marsProperty: MovieProperty,
                      app: Application) : AndroidViewModel(app) {
    private val _selectedProperty = MutableLiveData<MovieProperty>()

    val selectedProperty: LiveData<MovieProperty>
        get() = _selectedProperty

    init {
        _selectedProperty.value = marsProperty
    }



}

