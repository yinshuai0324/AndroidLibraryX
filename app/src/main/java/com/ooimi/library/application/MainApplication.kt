package com.ooimi.library.application

import android.app.Application
import com.ooimi.library.network.MainRequestResultHandler
import com.ooimi.network.NetworkManage
import com.ooimi.network.config.NetworkConfig

/**
 * @类作用描述:
 * @作者: 尹帅
 * @创建时间: 2022-09-06 15:14
 */
class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initNetwork()
    }

    private fun initNetwork() {
        val config = NetworkConfig().apply {
            isOpenLog = true
            requestSucceedCode = "0"
            baseUrl = hashMapOf<String, String>().apply {
                put(NetworkManage.KEY_DEFAULT_HOST, "https://www.baidu.com")
            }
            readTimeout = 3000
            writeTimeout = 3000
            logcatTag = "Okhttp"
            requestResultHandler = MainRequestResultHandler()
        }
        NetworkManage.setConfig(config).init()
    }
}