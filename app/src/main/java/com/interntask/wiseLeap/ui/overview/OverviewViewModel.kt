package com.interntask.wiseLeap.ui.overview

import android.util.Log
import androidx.lifecycle.*
import com.interntask.wiseLeap.network.Api
import com.interntask.wiseLeap.network.MarketItem
import kotlinx.coroutines.launch

enum class ApiStatus { LOADING, ERROR, DONE }

/**
 * The [ViewModel] that is attached to the [OverviewFragment].
 */
class OverviewViewModel : ViewModel() {

    private val _status = MutableLiveData<ApiStatus>()
    // The external immutable LiveData for the request status
    val status: LiveData<ApiStatus>
        get() = _status

    private val _currencyMarket = MutableLiveData<List<MarketItem>>()
    // The external LiveData interface to the property is immutable, so only this class can modify
    val currencyMarket: LiveData<List<MarketItem>>
        get() = _currencyMarket

    // LiveData to handle navigation to the selected property
    private val _navigateToSelectedItem = MutableLiveData<MarketItem?>()
    val navigateToSelectedItem: LiveData<MarketItem?>
        get() = _navigateToSelectedItem


    /**
     * Call getMarsRealEstateProperties() on init so we can display status immediately.
     */
    init {
        getCurrencyMarketInformation()
    }

    private fun getCurrencyMarketInformation() {
        viewModelScope.launch {
            _status.value = ApiStatus.LOADING
            try {
                //Log.i("retrofitService", "trying...")

                val response = Api.retrofitService.getProperties()
                _currencyMarket.value = response.body()?.markets
                _status.value = ApiStatus.DONE

                //Log.i("retrofitService", "loaded data... from API")

            } catch (e: Exception) {

                _status.value = ApiStatus.ERROR
                _currencyMarket.value = ArrayList()

                //Log.i("retrofitService", "error...${e.message}")
            }
        }
    }

    /**
     * When the property is clicked, set the [_navigateToSelectedItem] [MutableLiveData]
     * @param marketItem The [MarketItem] that was clicked on.
     */
    fun displayDetails(marketItem: MarketItem) {
        _navigateToSelectedItem.value = marketItem
    }
    /**
     * After the navigation has taken place, make sure navigateToSelectedProperty is set to null
     */
    fun displayDetailsComplete() {
        _navigateToSelectedItem.value = null
    }
}
