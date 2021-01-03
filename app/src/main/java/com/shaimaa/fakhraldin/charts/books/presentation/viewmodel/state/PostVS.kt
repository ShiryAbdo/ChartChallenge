package com.shaimaa.fakhraldin.charts.books.presentation.viewmodel.state

import com.shaimaa.fakhraldin.charts.books.presentation.model.PostVM

sealed class PostVS {
    class Post(val postVM: PostVM): PostVS()
    class Error(val message:String?): PostVS()
    class ShowLoader(val showLoader:Boolean): PostVS()
}