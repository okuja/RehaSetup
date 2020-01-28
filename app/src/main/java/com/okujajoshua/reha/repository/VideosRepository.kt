package com.okujajoshua.reha.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.okujajoshua.reha.database.RehaDatabase
import com.okujajoshua.reha.database.video.asDomainModel
import com.okujajoshua.reha.domain.DevByteVideo
import com.okujajoshua.reha.network.DevByteNetwork
import com.okujajoshua.reha.network.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

class VideosRepository(private val database : RehaDatabase) {

    val videos: LiveData<List<DevByteVideo>> = Transformations.map(database.videoDao.getVideos()){
        it.asDomainModel()
    }

    suspend fun refreshVideos(){
        withContext(Dispatchers.IO){
            Timber.d("refresh videos is called")
            val playlist = DevByteNetwork.devbytes.getPlaylist()
            database.videoDao.insertAll(playlist.asDatabaseModel())
        }

    }
}