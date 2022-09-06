package com.ooimi.network.expand

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ooimi.network.NetworkManage
import com.ooimi.network.dsl.NetworkRequestDsl
import com.ooimi.network.request.ApiRequest
import kotlinx.coroutines.launch

/**
 * @作用描述: 配合ViewModel使用
 * @作者: 尹帅
 * @创建时间: 2022-09-06 15:30
 */

/**
 * api请求 同步
 */
suspend fun <T> ViewModel.apiRequestAwait(block: suspend () -> T?): T? {
    return ApiRequest.apiRequestAwait(block)
}

/**
 * Api请求 同步 安全调用
 */
suspend fun <T> ViewModel.safeApiRequestAwait(block: suspend () -> T?): T? {
    return ApiRequest.safeApiRequestAwait(block)
}

/**
 * Api请求 异步 安全调用
 */
fun <T> ViewModel.safeApiRequest(dsl: NetworkRequestDsl<T>.() -> Unit) {
    viewModelScope.launch { ApiRequest.safeApiRequest(this, dsl) }
}

/**
 * 根据HostTag获取ApiService
 * @key hostKey
 */
fun <T> ViewModel.getApiService(key: String, apiService: Class<T>): T {
    return NetworkManage.createApiService(key, apiService)
}

/**
 * 获取默认的ApiService
 */
fun <T> ViewModel.getDefaultApiService(apiService: Class<T>): T {
    return NetworkManage.getDefaultRetrofitInstance().create(apiService)
}

/**
 * 安全请求作用域
 */
fun ViewModel.safeLaunch(block: suspend () -> Unit) {
    viewModelScope.launch {
        try {
            block.invoke()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}