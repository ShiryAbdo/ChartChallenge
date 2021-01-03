package com.shaimaa.fakhraldin.charts.books.data.datasource.rest

import com.shaimaa.fakhraldin.charts.core.network.BaseApiClient
import com.shaimaa.fakhraldin.charts.books.data.datasource.rest.interfaces.IApiClient

object PostApiClient : BaseApiClient<IApiClient>(IApiClient::class.java)