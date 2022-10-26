package com.ooimi.network

import android.util.Log
import android.util.Log.VERBOSE
import com.ihsanbal.logging.Level
import com.ihsanbal.logging.LoggingInterceptor
import com.moczul.ok2curl.CurlInterceptor
import com.moczul.ok2curl.logger.Logger
import com.ooimi.network.ssl.SSLManager
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.NullPointerException
import java.net.Proxy
import java.util.concurrent.TimeUnit

/**
 * 网络框架管理
 */
object NetworkLibrary {
    /**
     * 配置信息
     */
    private var config = NetworkLibraryBuilder()

    /**
     * okHttp客户端
     */
    private lateinit var okHttpClient: OkHttpClient

    /**
     * retrofit实例
     */
    internal var retrofitInstance: HashMap<String, Retrofit> = hashMapOf()

    /**
     * 默认的HostKey
     */
    const val KEY_DEFAULT_HOST = "DEFAULT_HOST"

    /**
     * 开始配置
     */
    private fun config() {
        //OkhttpClientBuilder
        val okHttpClientBuilder = if (config.isSupportHttps) {
            SSLManager.getSSLSocketFactory()
        } else {
            OkHttpClient.Builder()
        }
        //设置配置信息
        config.interceptors.forEach {
            okHttpClientBuilder.addInterceptor(it)
        }
        //设置网络拦截器
        config.networkInterceptors.forEach {
            okHttpClientBuilder.addNetworkInterceptor(it)
        }
        //是否开启Curl打印
        if (config.isOpenCurlLog) {
            //curl
            val curlInterceptor = CurlInterceptor(object : Logger {
                override fun log(message: String) {
                    Log.i(config.curlLogcatTag, message)
                }
            })
            okHttpClientBuilder.addInterceptor(curlInterceptor)
        }
        //是否开启日志打印
        if (config.isOpenLog) {
            val logInterceptor = LoggingInterceptor.Builder()
                .setLevel(Level.BASIC)
                .log(VERBOSE)
                .tag(config.logcatTag)
                .build()
            okHttpClientBuilder.addInterceptor(logInterceptor)
        } else {
            //不走系统代理
            okHttpClientBuilder.proxy(Proxy.NO_PROXY)
        }

        //配置连接超时时间
        okHttpClientBuilder.connectTimeout(config.connectTimeout, TimeUnit.MILLISECONDS)
        //读取超时时间
        okHttpClientBuilder.readTimeout(config.readTimeout, TimeUnit.MILLISECONDS)
        //写入超时时间
        okHttpClientBuilder.writeTimeout(config.writeTimeout, TimeUnit.MILLISECONDS)
        //配置结束
        okHttpClient = okHttpClientBuilder.build()
        //清除缓存
        retrofitInstance.clear()
        //判断是否有多个Host
        if (config.baseUrl.size <= 0) {
            Log.e(config.logcatTag, "没有配置Host,请检查！！！")
        } else {
            //多个或者单个Host
            config.baseUrl.forEach {
                retrofitInstance[it.key] = getRetrofitInstance(it.value)
            }
        }
    }


    /**
     * 获取Retrofit实例
     */
    private fun getRetrofitInstance(host: String): Retrofit {
        val instance = Retrofit.Builder()
        instance.baseUrl(host)
        instance.addConverterFactory(GsonConverterFactory.create())
        instance.client(okHttpClient)
        instance.build()
        return instance.build()
    }


    /**
     * 根据Host 创建ApiService
     */
    @JvmStatic
    fun <T> createApiService(key: String = KEY_DEFAULT_HOST, clazz: Class<T>): T {
        if (retrofitInstance.containsKey(key)) {
            val instance = retrofitInstance[key]
            if (instance != null) {
                return instance.create(clazz)
            } else {
                throw NullPointerException("The instance does not exist")
            }
        } else {
            throw NullPointerException("The incoming Host does not exist")
        }
    }


    /**
     * 获取全局的Retrofit实例
     */
    @JvmStatic
    fun getDefaultRetrofitInstance(): Retrofit {
        return getRetrofitInstance(config.baseUrl[KEY_DEFAULT_HOST] ?: "")
    }

    /**
     * 获取配置文件
     */
    internal fun getConfig(): NetworkLibraryBuilder {
        return config
    }

    /**
     * 获取OkHttpClient
     */
    @JvmStatic
    fun getOkhttpClient(): OkHttpClient {
        return okHttpClient
    }


    /**
     * 获取默认的ApiService
     */
    @JvmStatic
    fun <T> getDefaultApiService(api: Class<T>): T {
        return getDefaultRetrofitInstance().create(api)
    }

    /**
     * 获取默认的ApiService
     */
    @JvmStatic
    fun <T> getApiService(key: String, api: Class<T>): T {
        if (retrofitInstance.containsKey(key) && retrofitInstance[key] != null) {
            return retrofitInstance[key]!!.create(api)
        } else {
            throw Exception("获取ApiService时异常，未找到key:${key}对应的Retrofit实例")
        }
    }

    /**
     * 初始化
     */
    internal fun init(config: NetworkLibraryBuilder) {
        this.config = config
        config()
    }

}