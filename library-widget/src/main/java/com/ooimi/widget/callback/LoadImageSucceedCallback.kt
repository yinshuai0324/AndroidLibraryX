package com.ooimi.widget.callback

import java.lang.Exception

/**
 * @类作用描述:图片加载成功回掉
 * @作者: 尹帅
 * @创建时间: 2022-10-18 16:49
 */
abstract class LoadImageSucceedCallback : LoadImageCallback {
    override fun onLoadFailure(exception: Exception?, url: String?) {

    }

}