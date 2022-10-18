package com.ooimi.widget.imp

import android.widget.ImageView
import com.ooimi.widget.callback.LoadImageCallback

/**
 * @接口作用描述:
 * @作者: 尹帅
 * @创建时间: 2022-10-18 16:06
 */
abstract class BaseLoadImageModel {
    /**
     * 加载图片
     * @param view 图片控件
     * @param url 图片链接
     * @param loadingRes 加载中的占位图
     * @param errorRes 加载失败的占位图
     * @param loadAnim 是否开启加载动画
     * @param callback 图片加载回掉
     */
    abstract fun loadImage(
        view: ImageView,
        url: String,
        loadingRes: Int,
        errorRes: Int,
        loadAnim: Boolean,
        callback: LoadImageCallback
    )
}