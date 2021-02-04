package com.example.android.marsrealestate.ui.overview

import android.app.Application
import androidx.lifecycle.*
import androidx.lifecycle.MutableLiveData
import com.example.android.marsrealestate.network.MoviesApi
import com.example.android.marsrealestate.network.MarsApiFilter
import com.example.android.marsrealestate.database.getDatabase
import com.example.android.marsrealestate.network.MovieProperty
import com.example.android.marsrealestate.repository.MoviesRepository
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
