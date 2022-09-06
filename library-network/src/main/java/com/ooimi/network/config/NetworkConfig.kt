package com.ooimi.network.config

import com.ooimi.network.handler.BaseRequestResultHandler
import okhttp3.Interceptor

/**
 * @类作用描述:网络框架配置信息
 * @作者: 尹帅
 * @创建时间: 2022-09-05 18:13
 */
class NetworkConfig {
    /**
     * 是否打印日志
     */
    var isOpenLog: Boolean = false

    /**
     * baseUrl
     */
    var baseUrl = hashMapOf<String, String>()

    /**
     * 是否支持Https
     */
    var isSupportHttps: Boolean = true

    /**
     * 拦截器
     */
    var interceptor: List<Interceptor> = arrayListOf()

    /**
     * 请求拦截器
     */
    var requestResultHandler: BaseRequestResultHandler? = null

    /**
     * 请求成功时的Code
     */
    var requestSucceedCode: String = "200"

    /**
     * 连接超时时间
     */
    var connectTimeout: Long = 3000

    /**
     * 读取超时时间
     */
    var readTimeout: Long = 3000

    /**
     * 写入超时时间
     */
    var writeTimeout: Long = 3000

    /**
     * 日志打印Tag
     */
    var logcatTag: String = "OkHttp"
}