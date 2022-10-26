package com.ooimi.network

import com.ooimi.network.handler.BaseRequestResultHandler
import okhttp3.Interceptor

/**
 * @类作用描述:网络框架参数配置
 * @作者: 尹帅
 * @创建时间: 2022-10-13 19:33
 */
class NetworkLibraryBuilder {
    /**
     * 是否启用日志
     */
    internal var isOpenLog: Boolean = true

    /**
     * 是否开启Curl打印
     */
    internal var isOpenCurlLog: Boolean = false

    /**
     * 拦截器
     */
    internal val interceptors: ArrayList<Interceptor> = arrayListOf()

    /**
     * 网络拦截器
     */
    internal val networkInterceptors: ArrayList<Interceptor> = arrayListOf()

    /**
     * 是否支持Https
     */
    internal var isSupportHttps: Boolean = true

    /**
     * baseUrl
     */
    internal val baseUrl = HashMap<String, String>()

    /**
     * 请求事件拦截器
     */
    internal var requestResultHandler: BaseRequestResultHandler? = null

    /**
     * 业务请求成功时的Code
     */
    internal var requestSucceedCode: String = "200"

    /**
     * 连接超时时间
     */
    internal var connectTimeout: Long = 3000

    /**
     * 读取超时时间
     */
    internal var readTimeout: Long = 3000

    /**
     * 写入超时时间
     */
    internal var writeTimeout: Long = 3000

    /**
     * 日志打印的Tag
     */
    internal var logcatTag: String = "OkHttp"

    /**
     * Curl日志Tag
     */
    internal var curlLogcatTag: String = "Curl"

    fun setOpenLog(isOpen: Boolean): NetworkLibraryBuilder {
        this.isOpenLog = isOpen
        return this
    }

    fun setOpenCurlLog(isOpen: Boolean): NetworkLibraryBuilder {
        this.isOpenCurlLog = isOpen
        return this
    }

    fun addInterceptor(interceptor: Interceptor): NetworkLibraryBuilder {
        interceptors.add(interceptor)
        return this
    }

    fun addNetworkInterceptor(interceptor: Interceptor): NetworkLibraryBuilder {
        networkInterceptors.add(interceptor)
        return this
    }

    fun setIsSupportHttps(isSupportHttps: Boolean): NetworkLibraryBuilder {
        this.isSupportHttps = isSupportHttps
        return this
    }

    fun addBaseUrl(key: String, url: String): NetworkLibraryBuilder {
        baseUrl[key] = url
        return this
    }

    fun setRequestResultHandler(handler: BaseRequestResultHandler?): NetworkLibraryBuilder {
        this.requestResultHandler = handler
        return this
    }

    fun setRequestSucceedCode(code: String): NetworkLibraryBuilder {
        this.requestSucceedCode = code
        return this
    }

    fun setConnectTimeout(time: Long): NetworkLibraryBuilder {
        this.connectTimeout = time
        return this
    }

    fun setReadTimeout(time: Long): NetworkLibraryBuilder {
        this.readTimeout = time
        return this
    }

    fun setWriteTimeout(time: Long): NetworkLibraryBuilder {
        this.writeTimeout = time
        return this
    }

    fun setLogcatTag(tag: String): NetworkLibraryBuilder {
        this.logcatTag = tag
        return this
    }

    fun setCurlLogcatTag(tag: String): NetworkLibraryBuilder {
        this.curlLogcatTag = tag
        return this
    }

    fun init() = NetworkLibrary.init(this)
}