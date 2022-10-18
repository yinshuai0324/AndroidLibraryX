package com.ooimi.widget.callback

import java.lang.Exception

/**
 * @接口作用描述:加载图片回掉
 * @作者: 尹帅
 * @创建时间: 2022-10-18 16:12
 */
interface LoadImageCallback {
    fun onLoadSucceed()
    fun onLoadFailure(exception: Exception?, url: String?)
}