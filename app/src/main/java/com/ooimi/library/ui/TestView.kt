package com.ooimi.library.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import com.duodian.common.expand.dp
import com.ooimi.library.R

/**
 * @类作用描述:
 * @作者: 尹帅
 * @创建时间: 2022-10-17 10:54
 */
class TestView : AppCompatImageView {
    constructor(context: Context) : this(context, null)
    constructor(context: Context, attributeSet: AttributeSet?) : super(context, attributeSet) {
        setBackgroundColor(ContextCompat.getColor(context, android.R.color.transparent))
    }

    private val roundValue = 20f
    private val clipPath = Path()
    private val borderPath = Path()
    private val borderRectF = RectF()
    private val viewRectF = RectF()
    private val paint = Paint()
    private val roundArray = floatArrayOf(
        roundValue,
        roundValue,
        roundValue,
        roundValue,
        roundValue,
        roundValue,
        roundValue,
        roundValue
    )

    override fun onDraw(canvas: Canvas) {
        viewRectF.set(0f, 0f, width.toFloat(), height.toFloat())
        clipPath.addRoundRect(viewRectF, roundArray, Path.Direction.CCW)
        canvas.clipPath(clipPath)
        canvas.drawColor(ContextCompat.getColor(context, com.ooimi.widget.R.color.color_3A5FCD))
        super.onDraw(canvas)
        drawBorder(canvas)
    }


    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                this.alpha = 0.5f
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                this.alpha = 1f
            }
        }
        return super.onTouchEvent(event)
    }


    /**
     * 绘制边框
     */
    private fun drawBorder(canvas: Canvas) {
        borderRectF.left = viewRectF.left
        borderRectF.top = viewRectF.top
        borderRectF.right = viewRectF.right
        borderRectF.bottom = viewRectF.bottom
        borderPath.addRoundRect(borderRectF, roundArray, Path.Direction.CW)
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 5f.dp
        paint.color = ContextCompat.getColor(context, com.ooimi.widget.R.color.color_EF4136)
        canvas.drawPath(borderPath, paint)
    }
}