package com.interntask.wiseLeap.network

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MarketItem(
    val exchange_id: String,
    val symbol: String,
    val price_unconverted: Double,
    val price: Double,
    val change_24h: Double,
    val spread: Double,
    val volume_24h: Double,
    val status: String,
    val time: String
) : Parcelable {
    val hasDecreased
        get() = change_24h < 0
    val change24h: String
        get() {
                if(hasDecreased)
                    return ("%.2f".format(change_24h))
                else
                    return ("+"+"%.2f".format(change_24h))
        }
    val priceUnconverted
        get() = "%.2f".format(price_unconverted)
    val spreadString
        get() = "%.2f".format(spread)
    val volume24h
        get() = "%.2f".format(volume_24h)
    val priceString
        get() = "%.2f".format(price)
    val timeString
        get() = time.substring(11)
}
