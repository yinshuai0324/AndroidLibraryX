package com.ooimi.library.application

import android.app.Application
import com.ooimi.base.BaseLibraryBuilder
import com.ooimi.library.network.MainRequestResultHandler
import com.ooimi.network.NetworkLibrary
import com.ooimi.network.NetworkLibraryBuilder

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
        NetworkLibraryBuilder()
            .addBaseUrl(NetworkLibrary.KEY_DEFAULT_HOST, "https://www.baidu.com")
            .setOpenLog(true)
            .setIsSupportHttps(true)
            .setLogcatTag("OkHttp")
            .setRequestResultHandler(MainRequestResultHandler())
            .init()
        BaseLibraryBuilder().init()
    }
}