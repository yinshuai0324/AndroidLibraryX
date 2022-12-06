package com.ooimi.widget.image

import android.content.Context
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.util.AttributeSet
import android.util.Log
import com.ooimi.widget.R
import com.ooimi.widget.layout.RoundHelper
import com.ooimi.widget.layout.RoundLayout
import java.lang.ref.WeakReference
import kotlin.math.max

/**
 * 创建者：yinshuai
 * 创建时间：2021/7/20 16:51
 * 作用描述：圆角图片
 */
open class RoundImageView : BaseImageView, RoundLayout {
    private val helper = RoundHelper(false)

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attributeSet: AttributeSet?) : super(context, attributeSet) {
        helper.init(context, attributeSet, this)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        helper.onSizeChange(w, h)
    }

    override fun onDraw(canvas: Canvas) {
        helper.onDrawBefore(canvas)
        super.onDraw(canvas)
        helper.onDrawAfter(canvas)
    }

    override fun dispatchDraw(canvas: Canvas) {
        helper.onDispatchDrawBefore(canvas)
        super.dispatchDraw(canvas)
        helper.onDispatchDrawAfter(canvas)
    }

    override fun setBorderWidth(width: Float) {
        helper.setBorderWidth(width)
    }

    override fun setBorderColor(color: Int) {
        helper.setBorderColor(color)
    }

    override fun setBorder(width: Float, color: Int) {
        helper.setBorder(width, color)
    }

    override fun setRadius(radius: Float) {
        helper.setRadius(radius)
    }

    override fun setRadius(topL: Float, topR: Float, bottomL: Float, bottomR: Float) {
        helper.setRadius(topL, topR, bottomL, bottomR)
    }

    override fun setIsCircle(circle: Boolean) {
        helper.setIsCircle(circle)
    }

    override fun setBackgroundColors(color: Int) {
        helper.setBackgroundColor(color)
    }

}