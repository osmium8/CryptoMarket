package com.interntask.wiseLeap.ui.detail

import android.app.Application
import androidx.lifecycle.*
import com.interntask.wiseLeap.R
import com.interntask.wiseLeap.network.MarketItem

/**
 *  The [ViewModel] associated with the [DetailFragment], containing information about the selected
 *  [MarketItem].
 */
class DetailViewModel(marketItem: MarketItem,
                      app: Application) : AndroidViewModel(app) {

    // The internal MutableLiveData for the selected property
    private val _selectedProperty = MutableLiveData<MarketItem>()

    // The external LiveData for the SelectedProperty
    val selectedProperty: LiveData<MarketItem>
        get() = _selectedProperty

    // Initialize the _selectedProperty MutableLiveData
    init {
        _selectedProperty.value = marketItem
    }

    val displayTime = Transformations.map(selectedProperty) {
        app.applicationContext.getString(R.string.display_time, it.timeString)
    }

    val displayUnconverted = Transformations.map(selectedProperty) {
        app.applicationContext.getString(R.string.display_unconverted, it.priceUnconverted)
    }

    val displayVolume24h = Transformations.map(selectedProperty) {
        app.applicationContext.getString(R.string.display_volume24h, it.volume24h)
    }

    val displayPrice= Transformations.map(selectedProperty) {
        app.applicationContext.getString(R.string.display_price, it.priceString)
    }

    val displayChange24h = Transformations.map(selectedProperty) {
        app.applicationContext.getString(R.string.display_change24h, it.change24h)
    }

    val displaySpread = Transformations.map(selectedProperty) {
        app.applicationContext.getString(R.string.display_spread, it.spreadString)
    }

    val displayStatus = Transformations.map(selectedProperty) {
        app.applicationContext.getString(R.string.display_status, it.status)
    }

    val displayExchangeId = Transformations.map(selectedProperty) {
        app.applicationContext.getString(R.string.display_exchange_id, it.exchange_id)
    }
}

