package com.example.marsrealestate.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.create
import retrofit2.http.GET
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Query


enum class MarsFilter(val value: String) {

    SHOW_RENT("rent"),
    SHOW_BUY("buy"),
    SHOW_ALL("all")
}

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()
private const val baseUrl = "https://mars.udacity.com/"
private val retroFit =
    Retrofit.Builder()
        .addConverterFactory(
            MoshiConverterFactory.create(moshi)
        ).baseUrl(
            baseUrl
        ).build()

interface MarsApiServices {
    @GET("realestate")
    suspend fun getProperties(@Query("filter") type: String): List<MarsProperty>
}


object MarsApi {
    val retroFitServices: MarsApiServices by lazy {
        retroFit.create(MarsApiServices::class.java)
    }
}