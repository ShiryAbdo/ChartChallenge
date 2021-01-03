package com.shaimaa.fakhraldin.charts.di

import com.shaimaa.fakhraldin.charts.books.data.datasource.rest.PostRestDataStore
import com.shaimaa.fakhraldin.charts.books.data.repository.PostRepositoryImpl
import com.shaimaa.fakhraldin.charts.books.domain.interactor.GetPostByIdInteractor
import com.shaimaa.fakhraldin.charts.books.domain.interactor.GetPostsInteractor
import com.shaimaa.fakhraldin.charts.books.domain.repository.PostRepository
import com.shaimaa.fakhraldin.charts.books.presentation.viewmodel.PostViewModel
import com.shaimaa.fakhraldin.charts.books.presentation.viewmodel.PostsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

private val postModule = module {

    //region ViewModel
    viewModel {
        PostsViewModel(get())
    }
    viewModel {
        PostViewModel(get())
    }
    //endregion

    //region Interactor
    single {
        GetPostsInteractor(
            get()
        )
    }
    single {
        GetPostByIdInteractor(
            get()
        )
    }
    //endregion

    //region Repository
    single<PostRepository> {
        PostRepositoryImpl(get())
    }
    //endregion

    //region Datastore
    single {
        PostRestDataStore()
    }
    //endregion
}

val modules = listOf(postModule)