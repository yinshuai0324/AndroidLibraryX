package com.ooimi.local

import android.content.Context

/**
 * @类作用描述:Base框架参数配置
 * @作者: 尹帅
 * @创建时间: 2022-10-13 19:59
 */
class LocalDataLibraryBuilder {
    fun init(context: Context) = LocalDataLibrary.init(context, this)
}