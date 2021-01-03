package com.shaimaa.fakhraldin.charts.books.data.repository

import com.shaimaa.fakhraldin.charts.books.data.datasource.rest.PostRestDataStore
import com.shaimaa.fakhraldin.charts.books.data.mapper.PostDataMapper
import com.shaimaa.fakhraldin.charts.books.domain.model.PostEntity
import com.shaimaa.fakhraldin.charts.books.domain.repository.PostRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PostRepositoryImpl(
    private val postRestDataStore: PostRestDataStore
) : PostRepository {

    private val mPostDataMapper by lazy { PostDataMapper() }

    override fun getPosts(): Flow<PostEntity> =
        postRestDataStore.getPosts().map{
            mPostDataMapper.map(it)
        }

}
