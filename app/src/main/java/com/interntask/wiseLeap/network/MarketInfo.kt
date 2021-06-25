package com.interntask.wiseLeap.network

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MarketInfo(
    val markets: List<MarketItem>
) : Parcelable {
    val isFetched
        get() = true // TODO()
}
