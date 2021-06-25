package com.interntask.wiseLeap.ui.detail

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.interntask.wiseLeap.network.MarketItem

/**
 * Simple ViewModel factory that provides the MarketInfo and context to the ViewModel.
 */
class DetailViewModelFactory(
    private val marsProperty: MarketItem,
    private val application: Application) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(marsProperty, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
