package com.ooimi.library

import android.text.TextUtils
import com.ooimi.base.viewmodel.BaseViewModel
import com.ooimi.library.network.api.MainApiService
import com.ooimi.network.dsl.TestClass
import com.ooimi.network.expand.*
import com.ooimi.network.request.ApiRequest

/**
 * @类作用描述:
 * @作者: 尹帅
 * @创建时间: 2022-09-06 15:45
 */
class MainActivityViewModel : BaseViewModel() {
    private val apiService = getDefaultApiService(MainApiService::class.java)

    fun getOrderInfo() {
        safeLaunch {
            val data = apiRequestAwait { apiService.test() }
        }
        safeApiRequest {
            api = { apiService.test() }
            onBeforeHandler {
                var newData = ""
                if (!TextUtils.isEmpty(it)) {
                    newData = it ?: ""
                } else {
                    newData = "空数据 补上"
                }
                newData
            }
            onSuccess {

            }
            onCustomHandler {
                return@onCustomHandler "1"
            }
            onCustomHandlerComplete {

            }
            onFailed { errorMsg, code ->

            }
            onHideLoading {

            }
        }
    }
}