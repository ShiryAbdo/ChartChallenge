package com.shaimaa.fakhraldin.charts.books.presentation.viewmodel.state

import com.shaimaa.fakhraldin.charts.books.presentation.model.PostVM

sealed class PostsVS {
    class AddPost(val postVM: PostVM): PostsVS()
    class Error(val message:String?): PostsVS()
    class ShowLoader(val showLoader:Boolean): PostsVS()
}