package com.example.android.moviesapp.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ModelMovie(val id: Int,
                        val description: String?,
                        val image: String,
                        val name: String) : Parcelable