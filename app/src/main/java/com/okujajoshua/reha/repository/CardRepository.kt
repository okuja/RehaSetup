package com.okujajoshua.reha.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.okujajoshua.reha.database.RehaDatabase
import com.okujajoshua.reha.database.card.Card
import com.okujajoshua.reha.database.card.asDomainModel
import com.okujajoshua.reha.domain.DomainCard
import com.okujajoshua.reha.network.reha.RehaApi
import com.okujajoshua.reha.network.reha.card.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber


class CardRepository(private val database: RehaDatabase, private val application: Application) {

    //TODO: Convert this to domain model
    val cards: LiveData<List<DomainCard>> = Transformations.map(database.cardDao.getAllCards()) {
        it.asDomainModel()
    }


    suspend fun createCardId() {
        withContext(Dispatchers.IO) {
            Timber.d("Create card id is called")

            val cardIdModel = List(1) { CardIdModel("4883836336860016", true) }
            val body = CardActivationBody(cardIdModel)
            val api = RehaApi.createApi(application)
            val response = api.createCardId(body)
            database.cardDao.insertAll(response.resource?.cards)
        }
    }

    //TODO: Connect to the API and get all cards belonging to the user
    suspend fun refreshCards() {
        withContext(Dispatchers.IO) {
            Timber.d("Refresh cards is called")
            database.cardDao.getAllCards()
        }
    }


}