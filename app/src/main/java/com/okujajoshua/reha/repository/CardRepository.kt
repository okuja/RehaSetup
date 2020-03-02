package com.okujajoshua.reha.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.okujajoshua.reha.database.RehaDatabase
import com.okujajoshua.reha.database.card.DatabaseCard
import com.okujajoshua.reha.database.card.asDomainAccount
import com.okujajoshua.reha.database.card.asDomainBalance
import com.okujajoshua.reha.database.card.asDomainModel
import com.okujajoshua.reha.domain.DomainAccount
import com.okujajoshua.reha.domain.DomainBalance
import com.okujajoshua.reha.domain.DomainCard
import com.okujajoshua.reha.network.reha.RehaApi
import com.okujajoshua.reha.network.reha.card.request.CardCreationBody
import com.okujajoshua.reha.network.reha.card.request.CardIdModel
import com.okujajoshua.reha.network.reha.card.response.asDatabaseAccount
import com.okujajoshua.reha.network.reha.card.response.asDatabaseBalance
import com.okujajoshua.reha.network.reha.card.response.asDatabaseCard
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber


class CardRepository(private val database: RehaDatabase, private val application: Application) {


    val cards: LiveData<List<DomainCard>> = Transformations.map(database.cardDao.getAllCards()) {
        it.asDomainModel()
    }


    //TODO:Refactor Static variable PAN
    suspend fun createCardId(pan:String) {
        withContext(Dispatchers.IO) {
            Timber.d("Create card id is called")

            val cardIdModel = List(1) {
                CardIdModel(
                    pan,
                    true
                )
            }
            val body =
                CardCreationBody(
                    cardIdModel
                )
            val api = RehaApi.createApi(application)
            val response = api.createCardId(body)
            database.cardDao.insertAll(response.resource?.cards?.asDatabaseCard())
        }
    }

    suspend fun refreshCardAccountDetails(cardId : String){
        withContext(Dispatchers.IO) {
            Timber.d("Account Details")
            val api = RehaApi.createApi(application)
            val response = api.getCardAndAccountDetails(cardId,true)
            database.cardDao.insertOne(response.resource?.card?.asDatabaseCard())
            database.accountDao.insertAll(response.resource?.card?.accounts?.asDatabaseAccount(cardId))
            database.balanceDao.insertAll(response.resource?.card?.accounts?.asDatabaseBalance())
        }
    }

    //TODO: Connect to the API and get all cards belonging to the user
    suspend fun refreshCards() {
        withContext(Dispatchers.IO) {
            Timber.d("Refresh cards is called")
            database.cardDao.getAllCards()
        }
    }

    suspend fun getCardByCardId(cardId: String): DomainCard{
        return withContext(Dispatchers.IO) {
            database.cardDao.getCardById(cardId).asDomainModel()
        }
    }

    suspend fun getCardAccounts(cardId: String) : List<DomainAccount> {
        return withContext(Dispatchers.IO) {
            database.accountDao.getAllByCardId(cardId).asDomainAccount(cardId)
        }
    }

    suspend fun getAccountBalance(accountAliasId:String?): List<DomainBalance>{
        return withContext(Dispatchers.IO) {
            database.balanceDao.getAllByAccountAliasId(accountAliasId).asDomainBalance(accountAliasId)
        }
    }


}