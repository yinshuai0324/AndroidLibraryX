package com.ooimi.network.data

import com.google.gson.Gson

/**
 * @类作用描述:Response基类
 * @作者: 尹帅
 * @创建时间: 2022-09-06 12:35
 */
abstract class BaseResponseBean<T> {
    /**
     * 业务端的请求是否成功
     */
    abstract fun isSucceed(): Boolean

    /**
     * 获取Body数据
     */
    abstract fun getBody(): T?

    /**
     * 获取请求的Message
     */
    abstract fun getMessage(): String?

    /**
     * 获取业务端的Code
     */
    abstract fun getCode(): Int

    /**
     * 获取当前服务器时间
     */
    abstract fun getTimestamp(): Long

    /**
     * 把当前类转成JsonString
     */
    fun toJsonString(): String? = Gson().toJson(this)
}