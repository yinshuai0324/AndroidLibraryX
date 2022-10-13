package com.ooimi.widget.image

import android.content.Context
import android.graphics.drawable.Drawable
import android.text.TextUtils
import android.util.AttributeSet
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.GlideException
import com.ooimi.widget.R
import com.ooimi.widget.callback.OnLoadingImageListener

/**
 * 创建者：yinshuai
 * 创建时间：2021/7/19 18:38
 * 作用描述：网络图片
 */

class NetworkRoundImageView : RoundImageView {
    private var imageUrl: String = ""
    private var loadingRes: Int = 0
    private var errorRes: Int = 0
    private var loadCallback: Boolean = false

    /**
     * 图片加载监听
     */
    lateinit var onLoaderListener: (isSucceed: Boolean) -> Unit

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attributeSet: AttributeSet?) : super(context, attributeSet) {
        val attrs = context.obtainStyledAttributes(attributeSet, R.styleable.NetworkRoundImageView)
        imageUrl = attrs.getString(R.styleable.NetworkImageView_url) ?: ""
        loadingRes = attrs.getResourceId(R.styleable.NetworkImageView_loadingRes, 0)
        errorRes = attrs.getResourceId(R.styleable.NetworkImageView_errorRes, 0)
        loadCallback = attrs.getBoolean(R.styleable.NetworkImageView_loadCallback, false)
        attrs.recycle()
        this.setImageResource(loadingRes)
        //加载图片
        load(imageUrl)
    }


    /**
     * 加载图片
     */
    fun load(url: String?) {
        if (!TextUtils.isEmpty(url)) {
            this.imageUrl = url ?: ""
            loadImage()
        }
    }

    /**
     * 加载默认的图片
     */
    fun loadDefault() {
        Glide.with(this).load(loadingRes).into(this)
    }

    /**
     * 加载图片 入队
     */
    private fun loadImage() {
        Glide.with(this).load(imageUrl).placeholder(loadingRes)
            .error(errorRes).listener(object : OnLoadingImageListener() {
                override fun onLoadImageSucceed(drawable: Drawable?) {
                    if (::onLoaderListener.isInitialized) {
                        onLoaderListener.invoke(true)
                    }
                }

                override fun onLoadImageFailed(exception: GlideException?) {
                    if (::onLoaderListener.isInitialized) {
                        onLoaderListener.invoke(false)
                    }
                }
            })
            .into(this)
    }
}