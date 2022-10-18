package com.ooimi.widget.image

import android.graphics.drawable.Drawable
import android.text.TextUtils
import android.util.Log
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory
import com.ooimi.widget.WidgetLibrary
import com.ooimi.widget.callback.LoadImageCallback
import com.ooimi.widget.callback.OnLoadingImageListener

/**
 * @类作用描述:
 * @作者: 尹帅
 * @创建时间: 2022-10-18 16:09
 */
class LoadImageHelper constructor(val view: ImageView) {
    private var imageUrl: String = ""

    /**
     * 加载图片
     */
    fun load(
        url: String?,
        loadingRes: Int,
        errorRes: Int,
        loadAnim: Boolean,
        callback: LoadImageCallback
    ) {
        if (TextUtils.isEmpty(url)) {
            callback.onLoadFailure(Exception("图片链接不能为空"), "")
            return
        }
        this.imageUrl = url ?: ""

        if (WidgetLibrary.config?.loadImageModel != null) {
            //如果实现了此接口 说明是自己处理图片加载
            WidgetLibrary.config?.loadImageModel?.loadImage(
                view,
                imageUrl,
                loadingRes,
                errorRes,
                loadAnim,
                callback
            )
        } else {
            //否则 使用自身的默认加载
            val factory = DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(loadAnim).build()
            Glide.with(view).load(imageUrl).placeholder(loadingRes)
                .error(errorRes).listener(object : OnLoadingImageListener() {
                    override fun onLoadImageSucceed(drawable: Drawable?) {
                        Log.i("===>>>", "load image succeed:${imageUrl}")
                        callback.onLoadSucceed()
                    }

                    override fun onLoadImageFailed(exception: GlideException?) {
                        Log.i("===>>>", "load image failure:${exception} url:${imageUrl}")
                        callback.onLoadFailure(exception, imageUrl)
                    }
                })
                .transition(DrawableTransitionOptions.withCrossFade(factory))
                .into(view)
        }
    }

    /**
     * 加载默认图片
     */
    fun loadDefault(loadingRes: Int) {
        Glide.with(view).load(loadingRes).into(view)
    }
}