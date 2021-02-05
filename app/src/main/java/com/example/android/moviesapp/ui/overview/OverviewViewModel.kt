package com.example.android.moviesapp.ui.overview

import android.app.Application
import androidx.lifecycle.*
import androidx.lifecycle.MutableLiveData
import com.example.android.moviesapp.database.getDatabase
import com.example.android.moviesapp.domain.ModelMovie
import com.example.android.moviesapp.repository.MoviesRepository
import kotlinx.coroutines.launch
import java.io.IOException

enum class MarsApiStatus { LOADING, ERROR, DONE }


class OverviewViewModel (application: Application): AndroidViewModel(application) {
    private val videosRepository = MoviesRepository(getDatabase(application))

    val movieslist = videosRepository.movies

    private var _eventNetworkError = MutableLiveData<Boolean>(false)

    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)
    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown

    private val _navigateToSelectedProperty = MutableLiveData<ModelMovie>()

    val navigateToSelectedProperty: LiveData<ModelMovie>
        get() = _navigateToSelectedProperty


    init {
        refreshDataFromRepository()
    }

    private fun refreshDataFromRepository() {
        viewModelScope.launch {
            try {
                videosRepository.refreshVideos()
                _eventNetworkError.value = false
                _isNetworkErrorShown.value = false

            } catch (networkError: IOException) {
                if(movieslist.value.isNullOrEmpty())
                    _eventNetworkError.value = true
            }
        }
    }

    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }

    fun displayPropertyDetails(movie: ModelMovie) {
        _navigateToSelectedProperty.value = movie
    }

    fun displayPropertyDetailsComplete() {
        _navigateToSelectedProperty.value = null
    }

    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(OverviewViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return OverviewViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }

}
