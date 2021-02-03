

package com.example.android.marsrealestate.network

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue


@Parcelize
data class Property (
        val id: String,
        @Json(name = "img_src") val imgSrcUrl: String,
        val type: String,
        val price: Double) : Parcelable {
    val isRental
        get() = type == "rent"
}


@Parcelize
data class MarsProperty(
        @Json(name = "limit") val id: String,
        @Json(name = "results") val results: List<@RawValue MovieProperty>,
        // used to map img_src from the JSON to imgSrcUrl in our class
        @Json(name = "offset") val imgSrcUrl: String,
        @Json(name = "error") val type: String,
        @Json(name = "status_code") val price: Double) : Parcelable {
    val isRental
        get() = type == "ok"

}

@Parcelize
data class MovieProperty (val id:Int, val description:String?, val image:@RawValue ImageProperty, val name:String) : Parcelable{

}

data class ImageProperty (val icon_url:String)
