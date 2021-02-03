package com.example.android.marsrealestate.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.marsrealestate.network.MarsApi
import com.example.android.marsrealestate.network.MarsApiFilter
import com.example.android.marsrealestate.network.MarsProperty
import androidx.lifecycle.viewModelScope
import com.example.android.marsrealestate.network.MovieProperty
import kotlinx.coroutines.launch

enum class MarsApiStatus { LOADING, ERROR, DONE }


class OverviewViewModel : ViewModel() {

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
                val apiObject=MarsApi.retrofitService.getProperties()

                _properties.value=apiObject.results
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
