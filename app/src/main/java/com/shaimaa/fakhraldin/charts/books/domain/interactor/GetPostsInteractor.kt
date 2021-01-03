package com.shaimaa.fakhraldin.charts.books.domain.interactor

import android.util.Log
import com.shaimaa.fakhraldin.charts.core.interactor.Interactor
import com.shaimaa.fakhraldin.charts.books.domain.model.PostEntity
import com.shaimaa.fakhraldin.charts.books.domain.repository.PostRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetPostsInteractor(
    private val postRepository: PostRepository
) : Interactor<Interactor.None, Flow<PostEntity>> {

    override fun execute(params: Interactor.None): Flow<PostEntity> {
        return postRepository.getPosts()
    }
}