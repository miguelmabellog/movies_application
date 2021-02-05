package com.example.android.moviesapp.ui.detail

import android.app.Application
import androidx.core.text.HtmlCompat
import androidx.lifecycle.*
import androidx.lifecycle.Transformations
import com.example.android.moviesapp.domain.ModelMovie

class DetailViewModel(movie: ModelMovie,
                      app: Application) : AndroidViewModel(app) {
    private val _selectedProperty = MutableLiveData<ModelMovie>()

    val selectedProperty: LiveData<ModelMovie>
        get() = _selectedProperty



    val description = Transformations.map(selectedProperty) { select ->
        var descriptionvar=select.description
        if(descriptionvar!=null){
            HtmlCompat.fromHtml(HtmlCompat.fromHtml(descriptionvar,0).toString(),0)
        }else
        {
            "There's no description , sorry"
        }

    }


    init {
        _selectedProperty.value = movie
    }



}

