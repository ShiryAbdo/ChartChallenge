package com.shaimaa.fakhraldin.charts.books.data.datasource.rest

import com.shaimaa.fakhraldin.charts.books.data.datasource.rest.response.ResponseData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PostRestDataStore {

    fun getPosts(): Flow<ResponseData> = flow {
        emit( PostApiClient.getApiClient().getPosts())

    }


}