package com.ooimi.network.handler

import com.ooimi.network.data.BaseResponseBean

/**
 * @类作用描述:请求结果处理
 * @作者: 尹帅
 * @创建时间: 2022-09-05 19:04
 */
interface BaseRequestResultHandler {

    /**
     * 获取到数据时回掉
     */
    fun onData(data: BaseResponseBean<*>, isShowToast: Boolean)

    /**
     * 发生异常时调用
     */
    fun onException(throwable: Throwable)
}