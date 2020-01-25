package com.okujajoshua.reha.presentation.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.okujajoshua.reha.network.MarsApi
import com.okujajoshua.reha.network.MarsProperty
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OverviewViewModel : ViewModel() {

    // The internal MutableLiveData String that stores the most recent response
    private val _response = MutableLiveData<String>()

    // The external immutable LiveData for the response String
    val response: LiveData<String>
        get() = _response

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(
        viewModelJob + Dispatchers.Main )

    /**
     * Call getMarsRealEstateProperties() on init so we can display status immediately.
     */
    init {
        getMarsRealEstateProperties()
    }

    /**
     * Sets the value of the status LiveData to the Mars API status.
     */
    private fun getMarsRealEstateProperties() {

//        MarsApi.retrofitService.getProperties().enqueue(
//            object: Callback<List<MarsProperty>> {
//                override fun onFailure(call: Call<List<MarsProperty>>, t: Throwable) {
//                    _response.value = "Failure: " + t.message
//                }
//
//                override fun onResponse(call: Call<List<MarsProperty>>, response: Response<List<MarsProperty>>) {
//                    _response.value ="Success: ${response.body()?.size} Mars properties retrieved"
//                }
//            })

        uiScope.launch {
            _response.value = getFromAPI()

        }


    }

    private suspend fun getFromAPI() : String{
        return withContext(Dispatchers.IO){
            try{
                var listResult = MarsApi.retrofitService.getProperties()

                "Success: ${listResult.size} Mars properties retrieved"
            }catch (e:Exception){
                "Failure: ${e.message}"
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
