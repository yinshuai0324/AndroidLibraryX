package com.ooimi.widget.layout

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout


/**
 * 创建者：yinshuai
 * 创建时间：2021/8/4 14:08
 * 作用描述：圆角约束布局
 */
open class RoundConstraintLayout : ConstraintLayout, RoundLayout {

    private val helper = RoundHelper(true)

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

    override fun setBackgroundColorRaw(color: Int) {
        helper.setBackgroundColorRaw(color)
    }
}