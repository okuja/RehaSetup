package com.okujajoshua.reha.presentation.overviewdetail

import android.app.Application
import androidx.lifecycle.*
import com.okujajoshua.reha.R
import com.okujajoshua.reha.network.MarsProperty


/**
 * The [ViewModel] that is associated with the [DetailFragment].
 */
class OverviewDetailViewModel(
    marsProperty: MarsProperty,
    app: Application) : AndroidViewModel(app) {

    private val _selectedProperty = MutableLiveData<MarsProperty>()
    val selectedProperty: LiveData<MarsProperty>
        get() = _selectedProperty

    init {
        _selectedProperty.value = marsProperty
    }

    val displayPropertyPrice = Transformations.map(selectedProperty) {
        app.applicationContext.getString(
            when (it.isRental) {
                true -> R.string.display_price_monthly_rental
                false -> R.string.display_price
            }, it.price)
    }

    val displayPropertyType = Transformations.map(selectedProperty) {
        app.applicationContext.getString(R.string.display_type,
            app.applicationContext.getString(
                when (it.isRental) {
                    true -> R.string.type_rent
                    false -> R.string.type_sale
                }))
    }


}
