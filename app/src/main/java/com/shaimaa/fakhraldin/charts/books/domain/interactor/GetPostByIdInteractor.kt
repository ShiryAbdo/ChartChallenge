package com.shaimaa.fakhraldin.charts.books.domain.interactor

import com.shaimaa.fakhraldin.charts.core.interactor.Interactor
import com.shaimaa.fakhraldin.charts.books.domain.model.PostEntity
import com.shaimaa.fakhraldin.charts.books.domain.repository.PostRepository
import kotlinx.coroutines.flow.Flow


class GetPostByIdInteractor(
    private val postRepository: PostRepository
) : Interactor<GetPostByIdInteractor.Params, Flow<PostEntity>> {

    override fun execute(params: Params): Flow<PostEntity> {
        return postRepository.getPosts()
    }

    data class Params(val id: String)
}