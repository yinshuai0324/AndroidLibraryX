package com.ooimi.library.network.api

import com.ooimi.library.bean.AppThemeBean
import com.ooimi.library.bean.ResponseBean
import com.ooimi.network.data.BaseResponseBean
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * @接口作用描述:
 * @作者: 尹帅
 * @创建时间: 2022-09-06 15:45
 */
interface MainApiService {

    @GET("/api/sdk/guide")
    suspend fun test(): ResponseBean<AppThemeBean>
}