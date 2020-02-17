package com.okujajoshua.reha.repository

import android.app.Application
import com.okujajoshua.reha.network.reha.RehaApi
import com.okujajoshua.reha.network.reha.card.CardActivationBody
import com.okujajoshua.reha.network.reha.card.CardIdModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber


class CardRepository(application: Application) {

    var x = application

    suspend fun createCardId() {
        withContext(Dispatchers.IO) {
            Timber.d("Create card id is called")

            val cardIdModel = List(1) { CardIdModel("4883836336860016", true) }
            val body = CardActivationBody(cardIdModel)
            val api = RehaApi.createApi(x)
            val response = api.createCardId(body)
        }
    }
}