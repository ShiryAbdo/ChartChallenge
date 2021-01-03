package com.shaimaa.fakhraldin.charts.books.presentation.activities

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.shaimaa.fakhraldin.charts.R
import com.shaimaa.fakhraldin.charts.books.presentation.adapters.RVPostAdapter
import com.shaimaa.fakhraldin.charts.books.presentation.viewmodel.PostsViewModel
import com.shaimaa.fakhraldin.charts.books.presentation.viewmodel.state.PostVS.*
import com.shaimaa.fakhraldin.charts.books.presentation.viewmodel.state.PostsVS
import com.shaimaa.fakhraldin.charts.core.utils.ext.color
import kotlinx.android.synthetic.main.custom_dialog.*
import kotlinx.android.synthetic.main.item_overview.*
import kotlinx.android.synthetic.main.post_activity.*
import kotlinx.android.synthetic.main.posts_activity.*
import kotlinx.android.synthetic.main.posts_activity.example2
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.math.abs


class PostsActivity : AppCompatActivity(), RVPostAdapter.Listener {

    private val viewModel: PostsViewModel by viewModel()
    private val PIE_ANIMATION_DURATION = 1500
    private val PIE_RADIUS = 75f

    var positiveDialog: Button? = null
    var negativeDialog: Button? = null
    var dialogBuilder: AlertDialog.Builder? = null
    var alertDialog: AlertDialog? = null


    @ExperimentalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.posts_activity)
        observeViewModel()



        viewModel.getPosts()
    }

    private fun observeViewModel() {
        viewModel.viewState.observe(this@PostsActivity, {
            when (it) {
                is PostsVS.AddPost -> {

                    val pieDataSet = PieDataSet(
                        listOf(
                            PieEntry(abs(it.postVM.RSRP!!).toFloat(), getString(R.string.active)),
                            PieEntry(
                                abs(it.postVM.RSRQ!!).toFloat(),
                                getString(R.string.recovered)
                            ),
                            PieEntry(abs(it.postVM.SINR!!).toFloat(), getString(R.string.deaths))
                        ), getString(R.string.covid19)
                    )

                    pieDataSet.colors = arrayListOf(
                        color(R.color.color_active),
                        color(R.color.color_recovered),
                        color(R.color.color_death)
                    )

                    val pieData = PieData(pieDataSet)
                    pieData.setDrawValues(false)

                    pie_chart.data = pieData
                    pie_chart.legend.isEnabled = false
                    pie_chart.description = null
                    pie_chart.holeRadius = PIE_RADIUS
                    pie_chart.setHoleColor(color(R.color.cinder_grey))
                    pie_chart.setDrawEntryLabels(false)
                    pie_chart.animateY(PIE_ANIMATION_DURATION, Easing.EaseInOutQuart)
                    pie_chart.invalidate()




                    txt_rsrp.text = "${it.postVM.RSRP}"
                    txt_rsrq.text = "${it.postVM.RSRQ}"
                    txt_sinr.text = "${it.postVM.SINR}"

                    example2.setOnClickListener { x ->
                      startActivity(Intent(this, PostActivity::class.java))

                    }
                    ConstraintLayout.setOnClickListener { x ->
                         startActivity(Intent(this, PostActivity::class.java))

                    }
                    CardView1.setOnClickListener { x ->
                        showDialogT(
                            if (it.postVM.RSRP != null) "${it.postVM.RSRP}" else "",
                            if (it.postVM.RSRQ != null) "${it.postVM.RSRQ}" else "",
                            if (it.postVM.RSRP != null) "${it.postVM.SINR}" else ""
                        )


                    }
                    CardView2.setOnClickListener { x ->
                        showDialogT(
                            if (it.postVM.RSRP != null) "${it.postVM.RSRP}" else "",
                            if (it.postVM.RSRQ != null) "${it.postVM.RSRQ}" else "",
                            if (it.postVM.RSRP != null) "${it.postVM.SINR}" else ""
                        )


                    }
                    CardView3.setOnClickListener { x ->
                        showDialogT(
                            if (it.postVM.RSRP != null) "${it.postVM.RSRP}" else "",
                            if (it.postVM.RSRQ != null) "${it.postVM.RSRQ}" else "",
                            if (it.postVM.RSRP != null) "${it.postVM.SINR}" else ""
                        )


                    }

                }

            }


        })
    }

    override fun onPostClicked(id: Int) {
     }

    @SuppressLint("CutPasteId")
    fun showDialogT(RSRP: String, RSRQ: String, SINR: String) {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.custom_dialog)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val tvRSRP = dialog.findViewById(R.id.tvRSRP) as TextView
        val tvRSRQ = dialog.findViewById(R.id.tvRSRQ) as TextView
        val tvSINR = dialog.findViewById(R.id.tvSINR) as TextView
        tvRSRP.text =  RSRP
        tvRSRQ.text = RSRQ
        tvSINR.text = SINR
        dialog.show()
    }
}
