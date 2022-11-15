package com.ooimi.widget.widget

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.ooimi.widget.layout.RoundHelper
import com.ooimi.widget.layout.RoundLayout
import com.ooimi.widget.R

/**
 * 创建者：yinshuai
 * 创建时间：2021/8/4 17:21
 * 作用描述：圆角TextView
 */
class RoundTextView : AppCompatTextView, RoundLayout {

    /**
     * 圆角工具类
     */
    private val helper = RoundHelper(false)

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attributeSet: AttributeSet?) : super(context, attributeSet) {
        helper.init(context, attributeSet, this)
        val attrs = context.obtainStyledAttributes(attributeSet, R.styleable.RoundTextView)
        attrs.recycle()
        //先设置为透明 后面自己绘制颜色
        setBackgroundColors(android.R.color.transparent)
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