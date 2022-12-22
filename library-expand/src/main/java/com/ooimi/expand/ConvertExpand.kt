package com.ooimi.expand

import android.content.res.Resources
import android.util.TypedValue

/**
 * 转换相关扩展方法
 */


/**
 * int 转dp
 */
val Int.dp: Int
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this.toFloat(),
        Resources.getSystem().displayMetrics
    ).toInt()

/**
 * Float 转dp
 */
val Float.dp: Float
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this,
        Resources.getSystem().displayMetrics
    )


/**
 * int 转sp
 */
val Int.sp: Int
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_SP,
        this.toFloat(),
        Resources.getSystem().displayMetrics
    ).toInt()

/**
 * Float 转sp
 */
val Float.sp: Float
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_SP,
        this,
        Resources.getSystem().displayMetrics
    )

/**
 * int 转px
 */
val Int.px: Int
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_PX,
        this.toFloat(),
        Resources.getSystem().displayMetrics
    ).toInt()

/**
 * Float 转px
 */
val Float.px: Float
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_PX,
        this,
        Resources.getSystem().displayMetrics
    )

/**
 * int 转pt
 */
val Int.pt: Int
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_PT,
        this.toFloat(),
        Resources.getSystem().displayMetrics
    ).toInt()

/**
 * Float 转pt
 */
val Float.pt: Float
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_PT,
        this,
        Resources.getSystem().displayMetrics
    )

/**
 * int 转in
 */
val Int.inn: Int
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_IN,
        this.toFloat(),
        Resources.getSystem().displayMetrics
    ).toInt()

/**
 * Float 转in
 */
val Float.inn: Float
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_IN,
        this,
        Resources.getSystem().displayMetrics
    )

/**
 * int 转in
 */
val Int.mm: Int
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_MM,
        this.toFloat(),
        Resources.getSystem().displayMetrics
    ).toInt()

/**
 * Float 转in
 */
val Float.mm: Float
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_MM,
        this,
        Resources.getSystem().displayMetrics
    )

