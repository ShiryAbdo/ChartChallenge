package com.shaimaa.fakhraldin.charts.books.presentation.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
 data class ChartData(
    @SerializedName("RSRP")
    val rSRP: List<RSRP>,
    @SerializedName("RSRQ")
    val rSRQ: List<RSRQ>,
    @SerializedName("SINR")
    val sINR: List<SINR>
) {
    @Keep
     data class RSRP(
        @SerializedName("Color")
        val color: String,
        @SerializedName("From")
        var from: String,
        @SerializedName("To")
        var to: String
    )

    @Keep
     data class RSRQ(
        @SerializedName("Color")
        val color: String,
        @SerializedName("From")
        var from: String,
        @SerializedName("To")
        var to: String
    )

    @Keep
     data class SINR(
        @SerializedName("Color")
        val color: String,
        @SerializedName("From")
        var from: String,
        @SerializedName("To")
        var to: String
    )
}