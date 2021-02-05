package com.example.android.marsrealestate.ui.detail

import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.android.marsrealestate.databinding.FragmentDetailBinding


class DetailFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val application = requireNotNull(activity).application
        val binding = FragmentDetailBinding.inflate(inflater)
        binding.lifecycleOwner = this

        val movie = DetailFragmentArgs.fromBundle(arguments!!).selectedProperty
        val viewModelFactory = DetailViewModelFactory(movie, application)
        val  viewmodel= ViewModelProvider(
                this, viewModelFactory).get(DetailViewModel::class.java)
        binding.viewModel=viewmodel

        //val mHtmlString=viewmodel.selectedProperty.value?.description
        //binding.priceValueText.setText((Html.fromHtml(Html.fromHtml(mHtmlString).toString())))

        return binding.root
    }
}