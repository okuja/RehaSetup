package com.okujajoshua.reha.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

/**
 * A retrofit service to fetch a devbyte playlist.
 */
interface DevbyteService {
//    @GET("devbytes")
//    fun getPlaylist(): Deferred<NetworkVideoContainer>

    @GET("devbytes")
    suspend  fun getPlaylist(): NetworkVideoContainer
}

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

/**
 * Main entry point for network access. Call like `DevByteNetwork.devbytes.getPlaylist()`
 */
object DevByteNetwork {

    // Configure retrofit to parse JSON and use coroutines
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://android-kotlin-fun-mars-server.appspot.com/")
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    val devbytes = retrofit.create(DevbyteService::class.java)

}