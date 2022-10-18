package com.ooimi.widget.image

import android.content.Context
import android.graphics.drawable.Drawable
import android.text.TextUtils
import android.util.AttributeSet
import android.util.Log
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory
import com.ooimi.widget.R
import com.ooimi.widget.callback.LoadImageCallback
import com.ooimi.widget.callback.OnLoadingImageListener
import java.lang.Exception

/**
 * 创建者：yinshuai
 * 创建时间：2021/7/19 18:38
 * 作用描述：网络图片
 */

class NetworkImageView : BaseImageView, LoadImageCallback {
    private var imageUrl: String = ""
    private var loadingRes: Int = 0
    private var errorRes: Int = 0
    private var loadCallback: Boolean = false
    private var loadAnim: Boolean = false
    private var animDuration: Int = 300

    private var helper = LoadImageHelper(this)

    /**
     * 图片加载监听
     */
    private var onLoaderListener: LoadImageCallback? = null

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attributeSet: AttributeSet?) : super(context, attributeSet) {
        val attrs = context.obtainStyledAttributes(attributeSet, R.styleable.NetworkImageView)
        imageUrl = attrs.getString(R.styleable.NetworkImageView_url) ?: ""
        loadingRes = attrs.getResourceId(R.styleable.NetworkImageView_loadingRes, 0)
        errorRes = attrs.getResourceId(R.styleable.NetworkImageView_errorRes, 0)
        loadCallback = attrs.getBoolean(R.styleable.NetworkImageView_loadCallback, false)
        loadAnim = attrs.getBoolean(R.styleable.NetworkImageView_loadAnim, false)
        animDuration = attrs.getInteger(R.styleable.NetworkImageView_animDuration, 300)
        this.setImageResource(loadingRes)
        attrs.recycle()

        if (isInEditMode) {
            setImageResource(loadingRes)
        } else {
            //加载图片
            load(imageUrl)
        }
    }

    /**
     * 加载图片
     */
    fun load(url: String?) {
        helper.load(url, loadingRes, errorRes, animDuration, loadAnim, this)
    }

    /**
     * 加载默认的图片
     */
    fun loadDefault() {
        helper.loadDefault(loadingRes)
    }

    /**
     * 设置加载图片监听
     */
    fun setOnImageLoadListener(listener: LoadImageCallback) {
        this.onLoaderListener = listener
    }


    override fun onLoadSucceed() {
        //回掉给外部处理
        onLoaderListener?.onLoadSucceed()
    }

    override fun onLoadFailure(exception: Exception?, url: String?) {
        //回掉给外部处理
        onLoaderListener?.onLoadFailure(exception, url)
    }


}