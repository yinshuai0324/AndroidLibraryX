package com.ooimi.library.network

import com.ooimi.network.data.ResponseBean
import com.ooimi.network.handler.BaseRequestResultHandler

/**
 * @类作用描述:请求结果回掉
 * @作者: 尹帅
 * @创建时间: 2022-09-06 15:20
 */
class MainRequestResultHandler : BaseRequestResultHandler {
    override fun onData(data: ResponseBean<*>) {
        when (data.getRequestCodeAsInt()) {
            0 -> {
                //成功
            }
            1 -> {
                //Token失效等等
            }
        }
    }
}