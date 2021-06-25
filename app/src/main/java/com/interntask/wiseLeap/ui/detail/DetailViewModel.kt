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
    private val _selectedItem = MutableLiveData<MarketItem>()

    // The external LiveData for the SelectedProperty
    val selectedItem: LiveData<MarketItem>
        get() = _selectedItem

    // Initialize the _selectedProperty MutableLiveData
    init {
        _selectedItem.value = marketItem
    }

    val displayTime = Transformations.map(selectedItem) {
        app.applicationContext.getString(R.string.display_time, it.timeString)
    }

    val displayUnconverted = Transformations.map(selectedItem) {
        app.applicationContext.getString(R.string.display_unconverted, it.priceUnconverted)
    }

    val displayVolume24h = Transformations.map(selectedItem) {
        app.applicationContext.getString(R.string.display_volume24h, it.volume24h)
    }

    val displayPrice= Transformations.map(selectedItem) {
        app.applicationContext.getString(R.string.display_price, it.priceString)
    }

    val displayChange24h = Transformations.map(selectedItem) {
        app.applicationContext.getString(R.string.display_change24h, it.change24h)
    }

    val displaySpread = Transformations.map(selectedItem) {
        app.applicationContext.getString(R.string.display_spread, it.spreadString)
    }

    val displayStatus = Transformations.map(selectedItem) {
        app.applicationContext.getString(R.string.display_status, it.status)
    }

    val displayExchangeId = Transformations.map(selectedItem) {
        app.applicationContext.getString(R.string.display_exchange_id, it.exchange_id)
    }
}

