package com.ooimi.network

import android.util.Log
import android.util.Log.VERBOSE
import com.ihsanbal.logging.Level
import com.ihsanbal.logging.LoggingInterceptor
import com.ooimi.network.config.NetworkConfig
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
object NetworkManage {
    /**
     * 配置信息
     */
    private var config = NetworkConfig()

    /**
     * okHttp客户端
     */
    private lateinit var okHttpClient: OkHttpClient

    /**
     * retrofit实例
     */
    private var retrofitInstance: HashMap<String, Retrofit> = hashMapOf()

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
        config.interceptor.forEach {
            okHttpClientBuilder.addInterceptor(it)
        }
        //是否开启日志打印
        if (config.isOpenLog) {
            val logInterceptor = LoggingInterceptor.Builder()
                .setLevel(Level.BASIC)
                .log(VERBOSE)
                .tag("OkHttp")
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
    fun getDefaultRetrofitInstance(): Retrofit {
        return getRetrofitInstance(KEY_DEFAULT_HOST)
    }

    /**
     * 获取配置文件
     */
    fun getConfig(): NetworkConfig {
        return config
    }

    /**
     * 设置配置文件
     */
    fun setConfig(config: NetworkConfig): NetworkManage {
        this.config = config
        return this
    }

    /**
     * 初始化
     */
    fun init() {
        config()
    }
}