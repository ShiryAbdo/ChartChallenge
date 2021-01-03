@file:Suppress("UNCHECKED_CAST")

package com.shaimaa.fakhraldin.charts.books.presentation.activities

import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.aachartmodel.aainfographics.aachartcreator.*
import com.github.mikephil.charting.data.*
import com.google.gson.Gson
import com.shaimaa.fakhraldin.charts.R
import com.shaimaa.fakhraldin.charts.books.presentation.model.ChartData
import com.shaimaa.fakhraldin.charts.books.presentation.viewmodel.PostViewModel
import kotlinx.android.synthetic.main.post_activity.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

 class PostActivity : AppCompatActivity() {

    private val viewModel: PostViewModel by viewModel()
     var tfLight: Typeface? = null



    @ExperimentalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.post_activity)
        tfLight = Typeface.createFromAsset(assets, "OpenSans-Light.ttf")
        setUpAAChartView(AAChartType.Bubble)
        fab.setOnClickListener {
            setUpAAChartView(getRandomQuote())
        }
        fab_back.setOnClickListener {
            onBackPressed()
        }
    }

    private fun setUpAAChartView(bubble: AAChartType) {
        AAChartView.aa_drawChartWithChartModel(configureBubbleChart(bubble))
    }


    private fun getRandomQuote(): AAChartType {
        val quotes = arrayOf(
            AAChartType.Bubble,
            AAChartType.Bar,
            AAChartType.Area,
            AAChartType.Column,
            AAChartType.Column
        )
        val randomValue = Random().nextInt(quotes.size)
        return quotes[randomValue]
    }

    private fun configureBubbleChart(randomQuote: AAChartType): AAChartModel {
        val jsonString = viewModel.loadJson(this)
        val users = Gson().fromJson(jsonString, ChartData::class.java)
        val numberRSRP = arrayOfNulls<Any>(users.rSRP.size)
        for (i in 0 until users.rSRP.size - 1) {
            val rSRP = users.rSRP[i]
            if (rSRP.from == "Min")
                rSRP.from = "-150"
            if (rSRP.to == "Max")
                rSRP.to = "150"
            numberRSRP[i] = (rSRP.to).toFloat()
        }


        val numberRSRQ = arrayOfNulls<Any>(users.rSRP.size)
        for (i in 0 until users.rSRQ.size - 1) {
            val rSRP = users.rSRQ[i]
            if (rSRP.from == "Min")
                rSRP.from = "-150"
            if (rSRP.to == "Max")
                rSRP.to = "150"
            numberRSRQ[i] = (rSRP.to).toFloat()
        }

        val numberSINR = arrayOfNulls<Any>(users.rSRP.size)
        for (i in 0 until users.sINR.size - 1) {
            val rSRP = users.sINR[i]
            if (rSRP.from == "Min")
                rSRP.from = "-150"
            if (rSRP.to == "Max")
                rSRP.to = "150"
            numberSINR[i] = (rSRP.to).toFloat()
        }
        return AAChartModel()
            .chartType(randomQuote)
            .title("AACHARTKIT BUBBLES")
            .subtitle("JUST FOR FUN")
            .yAxisTitle("℃")
            .gradientColorEnable(true)
            .backgroundColor("#333340")
            .axesTextColor("#817D7D")

            .colorsTheme(arrayOf("#147ad6", "#ec6666", "#79d2de"))
            .series(
                arrayOf(
                    AASeriesElement()
                        .name("RSRP")
                        .data(numberRSRP as Array<Any>),
                    AASeriesElement()
                        .name("RSRQ")
                        .data(numberRSRQ as Array<Any>),
                    AASeriesElement()
                        .name("SINR")
                        .data(numberSINR as Array<Any>),

                    )
            )
    }


}
