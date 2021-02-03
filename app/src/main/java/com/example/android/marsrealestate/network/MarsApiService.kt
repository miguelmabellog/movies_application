package com.example.android.marsrealestate.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

enum class MarsApiFilter(val value: String) {
    SHOW_RENT("rent"),
    SHOW_BUY("buy"),
    SHOW_ALL("all") }

private const val BASE_URL = "https://comicvine.gamespot.com/api/"


private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()


private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL)
        .build()


interface MarsApiService {
    companion object {
        const val KEY = "0acbc1efdce6b190624cd64a61372a83ffeb3f6c"
    }

    @GET("movies/?api_key=$KEY&format=json")
    //suspend fun getProperties(@Query("filter") type: String): List<MarsProperty>
    suspend fun getProperties(): MarsProperty
}


object MarsApi {
    val retrofitService : MarsApiService by lazy { retrofit.create(MarsApiService::class.java) }
}
