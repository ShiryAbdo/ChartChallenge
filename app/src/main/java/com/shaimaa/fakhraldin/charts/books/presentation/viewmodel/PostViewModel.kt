package com.shaimaa.fakhraldin.charts.books.presentation.viewmodel

import android.R
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.shaimaa.fakhraldin.charts.books.domain.interactor.GetPostByIdInteractor
import com.shaimaa.fakhraldin.charts.books.presentation.model.mapper.PostVMMapper
import com.shaimaa.fakhraldin.charts.books.presentation.viewmodel.state.PostVS
import com.shaimaa.fakhraldin.charts.core.platform.BaseViewModel
import com.shaimaa.fakhraldin.charts.core.utils.io
import com.shaimaa.fakhraldin.charts.core.utils.ui
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONTokener
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream


class PostViewModel(
    private val getPostByIdInteractor: GetPostByIdInteractor
) : BaseViewModel() {

    val viewState: LiveData<PostVS> get() = mViewState
    private val mViewState = MutableLiveData<PostVS>()


    fun getPostByID(context: Context) {
        viewModelScope.launch {
            mViewState.value = PostVS.ShowLoader(true)

            try {

                io {

                }
            } catch (e: Exception) {
                ui {
                    mViewState.value = PostVS.Error(e.message)
                }
            }
            mViewState.value = PostVS.ShowLoader(false)
        }
    }

    /**
     * Write a func to load json from the json_data.json
     */
    fun loadJson(context: Context): String? {
        var input: InputStream? = null
        val jsonString: String

        try {
            // Create InputStream
            input = context.assets.open("json_data.json")

            val size = input.available()

            // Create a buffer with the size
            val buffer = ByteArray(size)

            // Read data from InputStream into the Buffer
            input.read(buffer)

            // Create a json String
            jsonString = String(buffer)
            return jsonString;
        } catch (ex: Exception) {
            ex.printStackTrace()
        } finally {
            // Must close the stream
            input?.close()
        }

        return null
    }

}