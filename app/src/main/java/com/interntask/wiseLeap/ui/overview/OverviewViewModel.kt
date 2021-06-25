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

    // The internal MutableLiveData that stores the status of the most recent request
    private val _status = MutableLiveData<ApiStatus>()

    // The external immutable LiveData for the request status
    val status: LiveData<ApiStatus>
        get() = _status

    // Internally, we use a MutableLiveData, because we will be updating the List of MarketInfo
    // with new values
    private val _properties = MutableLiveData<List<MarketItem>>()

    // The external LiveData interface to the property is immutable, so only this class can modify
    val properties: LiveData<List<MarketItem>>
        get() = _properties

    // LiveData to handle navigation to the selected property
    private val _navigateToSelectedProperty = MutableLiveData<MarketItem?>()
    val navigateToSelectedProperty: LiveData<MarketItem?>
        get() = _navigateToSelectedProperty


    /**
     * Call getMarsRealEstateProperties() on init so we can display status immediately.
     */
    init {
        getMarsRealEstateProperties()
    }

    private fun getMarsRealEstateProperties() {
        viewModelScope.launch {
            _status.value = ApiStatus.LOADING
            try {
                Log.i("retrofitService", "trying...")
                val response = Api.retrofitService.getProperties()
                _properties.value = response.body()?.markets
                _status.value = ApiStatus.DONE
                Log.i("retrofitService", "loaded data... from API")
            } catch (e: Exception) {
                _status.value = ApiStatus.ERROR
                _properties.value = ArrayList()
                Log.i("retrofitService", "error...${e.message}")
            }
        }
    }

    /**
     * When the property is clicked, set the [_navigateToSelectedProperty] [MutableLiveData]
     * @param marketItem The [MarketItem] that was clicked on.
     */
    fun displayPropertyDetails(marketItem: MarketItem) {
        _navigateToSelectedProperty.value = marketItem
    }
    /**
     * After the navigation has taken place, make sure navigateToSelectedProperty is set to null
     */
    fun displayPropertyDetailsComplete() {
        _navigateToSelectedProperty.value = null
    }
}
