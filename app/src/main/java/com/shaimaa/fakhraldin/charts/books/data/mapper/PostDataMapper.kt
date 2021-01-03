package com.shaimaa.fakhraldin.charts.books.data.mapper

import com.shaimaa.fakhraldin.charts.core.mapper.Mapper
import com.shaimaa.fakhraldin.charts.books.data.datasource.rest.response.ResponseData
import com.shaimaa.fakhraldin.charts.books.domain.model.PostEntity

class PostDataMapper : Mapper<ResponseData, PostEntity> {
    override fun map(origin: ResponseData) =
        PostEntity(
            RSRP = origin.RSRP,
            RSRQ = origin.RSRQ,
            SINR = origin.SINR,
         )
}