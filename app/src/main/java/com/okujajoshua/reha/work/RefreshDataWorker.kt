package com.okujajoshua.reha.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.okujajoshua.reha.database.RehaDatabase
import retrofit2.HttpException
import timber.log.Timber

class RefreshDataWorker(appContext: Context, params: WorkerParameters) :
    CoroutineWorker(appContext, params) {

    companion object {
        const val WORK_NAME = "com.okujajoshua.reha.work.RefreshDataWorker"
    }

    override suspend fun doWork(): Result {
        val database = RehaDatabase.getInstance(applicationContext)
//        val repository = VideosRepository(database)
//        try {
//            repository.refreshVideos()
//            Timber.d("Work request for sync is run")
//
//        }catch (e: HttpException){
//            return Result.retry()
//        }
        return Result.success()
    }
}