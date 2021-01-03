package com.shaimaa.fakhraldin.charts.books.domain.repository

import com.shaimaa.fakhraldin.charts.books.domain.model.PostEntity
import kotlinx.coroutines.flow.Flow

interface PostRepository {
    fun getPosts(): Flow<PostEntity>
}