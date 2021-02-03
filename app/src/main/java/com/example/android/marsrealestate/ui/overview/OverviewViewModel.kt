package com.example.android.marsrealestate.ui.overview

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.marsrealestate.network.MoviesApi
import com.example.android.marsrealestate.network.MarsApiFilter
import androidx.lifecycle.viewModelScope
import com.example.android.marsrealestate.database.getDatabase
import com.example.android.marsrealestate.network.MovieProperty
import com.example.android.marsrealestate.repository.MoviesRepository
import kotlinx.coroutines.launch

enum class MarsApiStatus { LOADING, ERROR, DONE }


class OverviewViewModel (application: Application): ViewModel() {

    private val videosRepository = MoviesRepository(getDatabase(application))

    private val _status = MutableLiveData<MarsApiStatus>()

    val status: LiveData<MarsApiStatus>
        get() = _status

    private val _properties = MutableLiveData<List<MovieProperty>>()

    val properties: LiveData<List<MovieProperty>>
        get() = _properties

    private val _navigateToSelectedProperty = MutableLiveData<MovieProperty>()
    val navigateToSelectedProperty: LiveData<MovieProperty>
        get() = _navigateToSelectedProperty

    init {
        getMarsRealEstateProperties(MarsApiFilter.SHOW_ALL)
    }

    private fun getMarsRealEstateProperties(filter: MarsApiFilter) {
        viewModelScope.launch {
            _status.value = MarsApiStatus.LOADING
            try {
                //val apiObject=MoviesApi.RETROFIT_SERVICE.getProperties()
                val apiObject=videosRepository.refreshVideos()
                _properties.value=apiObject
                _status.value = MarsApiStatus.DONE
            } catch (e: Exception) {
                _status.value = MarsApiStatus.ERROR
                //_properties.value = ArrayList()
            }
        }
    }

    fun updateFilter(filter: MarsApiFilter) {
        getMarsRealEstateProperties(filter)
    }

    fun displayPropertyDetails(marsProperty: MovieProperty) {
        _navigateToSelectedProperty.value = marsProperty
    }

    fun displayPropertyDetailsComplete() {
        _navigateToSelectedProperty.value = null
    }
}
