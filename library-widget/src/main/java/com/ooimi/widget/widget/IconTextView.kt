package com.ooimi.widget.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.ooimi.widget.R
import com.ooimi.widget.expand.resources2Bitmap

class IconTextView : AppCompatTextView {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val drawRect = Rect()

    /***************参数部分****************/
    private var iconWidth: Float = 0f
    private var iconHeight: Float = 0f
    private var iconDirection: Int = 1
    private var iconSpacing: Float = 0f
    private var icon: Int = 0

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attributeSet: AttributeSet?) : super(context, attributeSet) {
        val attrs = context.obtainStyledAttributes(attributeSet, R.styleable.IconTextView)
        iconWidth = attrs.getDimension(R.styleable.IconTextView_iconWidth, 0f)
        iconHeight = attrs.getDimension(R.styleable.IconTextView_iconHeight, 0f)
        iconDirection = attrs.getInt(R.styleable.IconTextView_iconDirection, 1)
        iconSpacing = attrs.getDimension(R.styleable.IconTextView_iconSpacing, 0f)
        icon = attrs.getResourceId(R.styleable.IconTextView_icon, 0)
        attrs.recycle()
        initParams()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.let { drawIcon(it) }
    }

    /**
     * 初始化参数
     */
    private fun initParams() {
        if (icon == 0) {
            return
        }
        when (iconDirection) {
            1 -> {
                //left
                val left = (paddingLeft + iconWidth + iconSpacing).toInt()
//                setPadding(left, paddingTop, paddingRight, paddingBottom)
                //icon绘制区域
                val iconLeft = paddingLeft
                val iconTop = ((height / 2) - (iconHeight / 2)).toInt()
                val iconRight = (iconLeft + iconWidth).toInt()
                val iconBottom = (iconTop + iconHeight).toInt()
                drawRect.set(iconLeft, iconTop, iconRight, iconBottom)
            }
            2 -> {
                //top
                val top = (paddingTop + iconWidth + iconSpacing).toInt()
                setPadding(paddingLeft, top, paddingRight, paddingBottom)
                //icon绘制区域
                val iconLeft = ((width / 2) - iconWidth / 2).toInt()
                val iconTop = paddingTop
                val iconRight = (iconLeft + iconWidth).toInt()
                val iconBottom = (iconTop + iconHeight).toInt()
                drawRect.set(iconLeft, iconTop, iconRight, iconBottom)
            }
            3 -> {
                //right
                val right = (paddingRight + iconWidth + iconSpacing).toInt()
                setPadding(paddingLeft, paddingTop, right, paddingBottom)
                //icon绘制区域
                val iconLeft = width - paddingRight
                val iconTop = ((height / 2) - iconHeight / 2).toInt()
                val iconRight = (iconLeft - iconWidth).toInt()
                val iconBottom = (iconTop + iconHeight).toInt()
                drawRect.set(iconLeft, iconTop, iconRight, iconBottom)
            }
            4 -> {
                //bottom
                val bottom = (paddingBottom + iconWidth + iconSpacing).toInt()
                setPadding(paddingLeft, paddingTop, paddingRight, bottom)
                //icon绘制区域
                val iconLeft = ((width / 2) - iconWidth / 2).toInt()
                val iconTop = (paddingTop - iconHeight).toInt()
                val iconRight = (iconLeft + iconWidth).toInt()
                val iconBottom = (iconTop + iconHeight).toInt()
                drawRect.set(iconLeft, iconTop, iconRight, iconBottom)
            }
        }
    }

    /**
     * 绘制图标
     */
    private fun drawIcon(canvas: Canvas) {
        if (icon == 0) {
            return
        }
        val bitmap = context.resources2Bitmap(icon) ?: return
        canvas.drawBitmap(bitmap, drawRect, drawRect, paint)

        paint.color = Color.BLUE
        canvas.drawRect(drawRect, paint)
    }

    /**
     * 设置Icon
     */
    fun setImageResources(res: Int) {
        icon = res
        invalidate()
    }

//    private fun setClipToPadding() {
//        if (hasBooleanFlag(FLAG_CLIP_TO_PADDING) != clipToPadding) {
//            //若是值有改变，则重新设置
//            setBooleanFlag(FLAG_CLIP_TO_PADDING, clipToPadding)
//            //刷新
//            invalidate()
//        }
//    }
}