package com.shaimaa.fakhraldin.charts.books.presentation.model.mapper

import com.shaimaa.fakhraldin.charts.core.mapper.Mapper
import com.shaimaa.fakhraldin.charts.books.domain.model.PostEntity
import com.shaimaa.fakhraldin.charts.books.presentation.model.PostVM

class PostVMMapper : Mapper<PostEntity, PostVM> {
    override fun map(origin: PostEntity) =
        PostVM(
            RSRP = origin.RSRP,
            RSRQ = origin.RSRQ,
            SINR = origin.SINR,
         )
}