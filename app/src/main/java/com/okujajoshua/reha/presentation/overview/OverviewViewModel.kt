package com.okujajoshua.reha.presentation.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.okujajoshua.reha.network.MarsApi
import com.okujajoshua.reha.network.MarsApiFilter
import com.okujajoshua.reha.network.MarsProperty
import kotlinx.coroutines.*


enum class MarsApiStatus { LOADING, ERROR, DONE }
class OverviewViewModel : ViewModel() {

    private val _status = MutableLiveData<MarsApiStatus>()

    val status: LiveData<MarsApiStatus>
        get() = _status

    private val _properties = MutableLiveData<List<MarsProperty>>()
    val properties : LiveData<List<MarsProperty>>
        get() = _properties

    private val _navigateToSelectedProperty = MutableLiveData<MarsProperty>()
    val navigateToSelectedProperty: LiveData<MarsProperty>
        get() = _navigateToSelectedProperty

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(
        viewModelJob + Dispatchers.Main )

    /**
     * Call getMarsRealEstateProperties() on init so we can display status immediately.
     */
    init {
        getMarsRealEstateProperties(MarsApiFilter.SHOW_ALL)
    }

    /**
     * Sets the value of the status LiveData to the Mars API status.
     */
    private fun getMarsRealEstateProperties(filter : MarsApiFilter) {
        _status.value = MarsApiStatus.LOADING

        uiScope.launch {

            try{
                val listResult = getFromAPI(filter.value)

                if(listResult.size > 0){
                    _status.value = MarsApiStatus.DONE
                    _properties.value = listResult
                }
            }catch (e:Exception){
                _status.value = MarsApiStatus.ERROR
                _properties.value = ArrayList()
            }
        }
    }

    private suspend fun getFromAPI(filter: String) : List<MarsProperty>{
        return withContext(Dispatchers.IO){
                val listResult = MarsApi.retrofitService.getProperties(filter)
                listResult
        }
    }

    fun updateFilter(filter: MarsApiFilter) {
        getMarsRealEstateProperties(filter)
    }

    fun displayPropertyDetails(marsProperty: MarsProperty) {
        _navigateToSelectedProperty.value = marsProperty
    }

    fun displayPropertyDetailsComplete() {
        _navigateToSelectedProperty.value = null
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
