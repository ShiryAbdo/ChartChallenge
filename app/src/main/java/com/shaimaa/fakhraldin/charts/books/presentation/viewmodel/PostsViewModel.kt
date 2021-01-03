package com.shaimaa.fakhraldin.charts.books.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.shaimaa.fakhraldin.charts.R
import com.shaimaa.fakhraldin.charts.core.interactor.Interactor
import com.shaimaa.fakhraldin.charts.core.platform.BaseViewModel
import com.shaimaa.fakhraldin.charts.core.utils.io
import com.shaimaa.fakhraldin.charts.core.utils.ui
import com.shaimaa.fakhraldin.charts.books.domain.interactor.GetPostsInteractor
import com.shaimaa.fakhraldin.charts.books.presentation.model.mapper.PostVMMapper
import com.shaimaa.fakhraldin.charts.books.presentation.viewmodel.state.PostsVS
import com.shaimaa.fakhraldin.charts.core.utils.ext.color
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class PostsViewModel(
    private val getPostsInteractor: GetPostsInteractor
) : BaseViewModel() {

    val viewState: LiveData<PostsVS> get() = mViewState
     private val mViewState = MutableLiveData<PostsVS>()

    private val mPostVMMapper by lazy { PostVMMapper() }


    fun getPosts() {
        viewModelScope.launch {
            mViewState.value = PostsVS.ShowLoader(true)
            try {
                io {
                    getPostsInteractor.execute(Interactor.None)
                        .collect {
                            ui { mViewState.value = PostsVS.AddPost(mPostVMMapper.map(it)) }
                        }
                }
            } catch (e: Exception) {
                ui { mViewState.value = PostsVS.Error(e.message) }
            }
            mViewState.value = PostsVS.ShowLoader(false)
        }
    }
}