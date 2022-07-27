package com.ooimi.widget.callback

import android.graphics.drawable.Drawable
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

/**
 * @author 尹帅
 * @date 2022/7/26 15:22
 * @desc
 */
abstract class OnLoadingImageListener : RequestListener<Drawable> {
    override fun onLoadFailed(
        e: GlideException?,
        model: Any?,
        target: Target<Drawable>?,
        isFirstResource: Boolean
    ): Boolean {
        onLoadImageFailed(e)
        return false
    }

    override fun onResourceReady(
        resource: Drawable?,
        model: Any?,
        target: Target<Drawable>?,
        dataSource: DataSource?,
        isFirstResource: Boolean
    ): Boolean {
        onLoadImageSucceed(resource)
        return false
    }

    abstract fun onLoadImageSucceed(drawable: Drawable?)
    abstract fun onLoadImageFailed(exception: GlideException?)
}