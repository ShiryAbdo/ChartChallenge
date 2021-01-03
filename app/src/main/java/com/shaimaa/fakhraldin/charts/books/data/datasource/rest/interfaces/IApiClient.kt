package com.shaimaa.fakhraldin.charts.books.data.datasource.rest.interfaces

import com.shaimaa.fakhraldin.charts.books.data.datasource.rest.response.ResponseData
import retrofit2.http.GET
import retrofit2.http.Path

interface IApiClient {
    @GET("/random")
    suspend fun getPosts(): ResponseData

}