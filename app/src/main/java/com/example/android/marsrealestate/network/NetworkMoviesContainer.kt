

package com.example.android.marsrealestate.network

import android.os.Parcelable
import com.example.android.marsrealestate.database.DatabaseMovies
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue



@Parcelize
data class NetworkMoviesContainer(
        val results: List<@RawValue MovieProperty>
        ) : Parcelable

@Parcelize
data class MovieProperty (val id:Int,
                          val description:String?,
                          val image:@RawValue ImageProperty,
                          val name:String) : Parcelable

data class ImageProperty (val icon_url:String)

fun NetworkMoviesContainer.asDatabaseModel(): List<DatabaseMovies> {
        return results.map {
                DatabaseMovies(
                        id = it.id,
                        description = it.description,
                        image = it.image.icon_url,
                        name = it.name)
        }
}
