package com.ooimi.widget.layout

import android.content.Context
import android.graphics.*
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import com.ooimi.widget.R

/**
 * 创建者：yinshuai
 * 创建时间：2021/8/4 15:56
 * 作用描述：圆角帮助类
 */
class RoundHelper constructor(val isViewGroup: Boolean) {

    private lateinit var mContext: Context

    /**
     * View的矩形
     */
    private val viewRectF = RectF()

    /**
     * 边框矩形
     */
    private val borderRectF = RectF()

    /**
     * 绘制的Path
     */
    private val drawPath = Path()

    /**
     * 圆角裁剪Path
     */
    private val roundDrawPath = Path()

    /**
     * 圆角值
     */
    private val roundParams: FloatArray = FloatArray(8)

    /**
     * 边框圆角参数
     */
    private val borderRoundParams: FloatArray = FloatArray(8)

    /**
     * 画笔
     */
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    /**
     * View
     */
    private lateinit var rootView: View

    /**********参数部分************/

    /**
     * 边框宽度
     */
    private var borderWidth = 0f

    /**
     * 边框颜色
     */
    private var borderColor = Color.parseColor("#FF0000")

    /**
     * 四个角的圆角
     */
    private var radius = 0f

    /**
     * 左上角圆角
     */
    private var radiusTopLeft = 0f

    /**
     * 右上角圆角
     */
    private var radiusTopRight = 0f

    /**
     * 左下角圆角
     */
    private var radiusBottomLeft = 0f

    /**
     * 右下角圆角
     */
    private var radiusBottomRight = 0f

    /**
     * 是否是圆
     */
    private var isCircle = false

    /**
     * View的宽度
     */
    private var width: Int = 0

    /**
     * View的高度
     */
    private var height: Int = 0

    /**
     * 背景颜色 初始化之后不再变
     */
    private var backgroundColor: Int = 0

    /**
     * 背景颜色 有可能会受外面动态改变而改变
     */
    private var tempBackgroundColor: Int = 0

    /**
     * 透明颜色
     */
    private val transparentColor = Color.parseColor("#00000000")


    /**
     * 初始化配置
     */
    fun init(context: Context, attributeSet: AttributeSet?, view: View) {
        mContext = context
        rootView = view
        if (rootView.background != null && rootView.background is ColorDrawable) {

        } else {
            //如果没有设置背景颜色 或者不是设置的颜色 则不用管
        }

        when (rootView.background) {
            is ColorDrawable -> {
                //颜色背景
                backgroundColor = (rootView.background as ColorDrawable).color
                tempBackgroundColor = backgroundColor
                rootView.setBackgroundColor(transparentColor)
            }
            is Drawable -> {
                //其他的背景 不用处理
            }
            else -> {
                //null
                //默认为透明 把背景设置为透明 等下自己绘制颜色
                rootView.setBackgroundColor(transparentColor)
            }
        }


        attributeSet?.let {
            val attr = context.obtainStyledAttributes(it, R.styleable.RoundLayout)
            borderWidth = attr.getDimension(R.styleable.RoundLayout_borderWidth, 0f)
            borderColor = attr.getColor(R.styleable.RoundLayout_borderColor, Color.WHITE)
            radiusTopLeft = attr.getDimension(R.styleable.RoundLayout_radiusTopLeft, 0f)
            radiusTopRight = attr.getDimension(R.styleable.RoundLayout_radiusTopRight, 0f)
            radiusBottomLeft = attr.getDimension(R.styleable.RoundLayout_radiusBottomLeft, 0f)
            radiusBottomRight = attr.getDimension(R.styleable.RoundLayout_radiusBottomRight, 0f)
            radius = attr.getDimension(R.styleable.RoundLayout_radius, 0f)
            isCircle = attr.getBoolean(R.styleable.RoundLayout_isCircle, false)
            attr.recycle()
        }
    }


    /**
     * 大小改变
     */
    fun onSizeChange(width: Int, height: Int) {
        this.width = width
        this.height = height
        updateParams()
    }

    /**
     * 更新绘制参数
     */
    private fun updateParams() {
        //更新View的最新宽高区域
        viewRectF.set(0f, 0f, width.toFloat(), height.toFloat())

        if (isCircle) {
            radius = height.coerceAtMost(width) * 1f / 2 - borderWidth
        }

        if (radius > 0) {
            radiusTopLeft = radius
            radiusTopRight = radius
            radiusBottomLeft = radius
            radiusBottomRight = radius
        }
        //更新圆角的值
        roundParams[0] = radiusTopLeft
        roundParams[1] = radiusTopLeft
        roundParams[2] = radiusTopRight
        roundParams[3] = radiusTopRight
        roundParams[4] = radiusBottomRight
        roundParams[5] = radiusBottomRight
        roundParams[6] = radiusBottomLeft
        roundParams[7] = radiusBottomLeft
        //圆角Path
        roundDrawPath.reset()
        roundDrawPath.addRoundRect(viewRectF, roundParams, Path.Direction.CCW)
        //更新边框圆角的值
        borderRoundParams[0] = radiusTopLeft
        borderRoundParams[1] = radiusTopLeft
        borderRoundParams[2] = radiusTopRight
        borderRoundParams[3] = radiusTopRight
        borderRoundParams[4] = radiusBottomRight
        borderRoundParams[5] = radiusBottomRight
        borderRoundParams[6] = radiusBottomLeft
        borderRoundParams[7] = radiusBottomLeft
    }


    /**
     * 在super.draw()之前绘制
     */
    fun onDrawBefore(canvas: Canvas) {
        if (!isViewGroup) {
            canvas.clipPath(roundDrawPath)
            canvas.drawColor(tempBackgroundColor)
        }
    }


    /**
     * 在super.draw()之后绘制
     */
    fun onDrawAfter(canvas: Canvas) {
        canvas.clipPath(roundDrawPath)
        if (isViewGroup) {
            canvas.drawColor(tempBackgroundColor)
        }
        if (!isViewGroup) {
            drawBorder(canvas)
        }
    }

    /**
     * 在super.dispatchDraw()之前绘制
     */
    fun onDispatchDrawBefore(canvas: Canvas) {
    }

    /**
     * 在super.dispatchDraw()之后绘制
     */
    fun onDispatchDrawAfter(canvas: Canvas) {
        if (isViewGroup) {
            drawBorder(canvas)
        }
    }

    /**
     * 绘制边框
     */
    private fun drawBorder(canvas: Canvas) {
        if (borderWidth > 0) {
            drawPath.reset()
            borderRectF.left = viewRectF.left
            borderRectF.top = viewRectF.top
            borderRectF.right = viewRectF.right
            borderRectF.bottom = viewRectF.bottom
            drawPath.addRoundRect(borderRectF, roundParams, Path.Direction.CW)
            paint.style = Paint.Style.STROKE
            paint.strokeWidth = borderWidth * 2
            paint.color = borderColor
            canvas.drawPath(drawPath, paint)
        }
    }

    /**
     * 绘制背景
     */
    private fun drawBackground(canvas: Canvas) {
        if (tempBackgroundColor != 0) {
            paint.color = tempBackgroundColor
            drawPath.addRoundRect(viewRectF, roundParams, Path.Direction.CW)
            canvas.drawPath(drawPath, paint)
        } else {
            //否则就是自己设置背景了
        }
    }


    /**
     * 设置边框宽度
     */
    fun setBorderWidth(width: Float) {
        borderWidth = width
        updateParams()
        rootView.invalidate()
    }


    /**
     * 设置边框颜色
     */
    fun setBorderColor(color: Int) {
        borderColor = getColor(color)
        updateParams()
        rootView.invalidate()
    }

    /**
     * 设置边框参数
     */
    fun setBorder(width: Float, color: Int) {
        borderWidth = width
        borderColor = getColor(color)
        updateParams()
        rootView.invalidate()
    }

    /**
     * 设置圆角
     */
    fun setRadius(radius: Float) {
        this.radius = radius
        updateParams()
        rootView.invalidate()
    }

    /**
     * 设置圆角
     */
    fun setRadius(topL: Float, topR: Float, bottomL: Float, bottomR: Float) {
        this.radiusTopLeft = topL
        this.radiusTopRight = topR
        this.radiusBottomLeft = bottomL
        this.radiusBottomRight = bottomR
        updateParams()
        rootView.invalidate()
    }


    /**
     * 设置背景颜色
     */
    fun setBackgroundColor(color: Int) {
        tempBackgroundColor = getColor(color)
        rootView.invalidate()
    }

    /**
     * 设置背景颜色
     */
    fun setBackgroundColorNotRes(color: Int) {
        tempBackgroundColor = color
        rootView.invalidate()
    }

    /**
     * 获取背景颜色
     */
    fun getBackgroundColor(): Int {
        return backgroundColor
    }

    /**
     * 设置是否是圆形
     */
    fun setIsCircle(circle: Boolean) {
        this.isCircle = circle
        updateParams()
        rootView.invalidate()
    }

    /**
     * 获取颜色
     */
    fun getColor(color: Int): Int {
        return ContextCompat.getColor(mContext, color)
    }

}