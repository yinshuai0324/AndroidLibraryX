package com.ooimi.library.network.api

import com.ooimi.network.data.ResponseBean
import retrofit2.http.POST

/**
 * @接口作用描述:
 * @作者: 尹帅
 * @创建时间: 2022-09-06 15:45
 */
interface MainApiService {

    @POST("/api/test")
    suspend fun test(): ResponseBean<String>
}