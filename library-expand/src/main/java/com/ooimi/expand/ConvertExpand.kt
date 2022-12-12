package com.ooimi.expand

import android.content.res.Resources
import android.util.TypedValue

/**
 * 转换相关扩展方法
 */


/**
 * int 转dp
 */
fun Int.dp(): Int {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this.toFloat(),
        Resources.getSystem().displayMetrics
    ).toInt()
}

/**
 * Float 转dp
 */
fun Float.dp(): Float {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this,
        Resources.getSystem().displayMetrics
    )
}


/**
 * int 转sp
 */
fun Int.sp(): Int {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_SP,
        this.toFloat(),
        Resources.getSystem().displayMetrics
    ).toInt()
}

/**
 * Float 转sp
 */
fun Float.sp(): Float {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_SP,
        this,
        Resources.getSystem().displayMetrics
    )
}

/**
 * int 转px
 */
fun Int.px(): Int {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_PX,
        this.toFloat(),
        Resources.getSystem().displayMetrics
    ).toInt()
}

/**
 * Float 转px
 */
fun Float.px(): Float {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_PX,
        this,
        Resources.getSystem().displayMetrics
    )
}

/**
 * int 转pt
 */
fun Int.pt(): Int {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_PT,
        this.toFloat(),
        Resources.getSystem().displayMetrics
    ).toInt()
}

/**
 * Float 转pt
 */
fun Float.pt(): Float {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_PT,
        this,
        Resources.getSystem().displayMetrics
    )
}

/**
 * int 转in
 */
fun Int.inn(): Int {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_IN,
        this.toFloat(),
        Resources.getSystem().displayMetrics
    ).toInt()
}

/**
 * Float 转in
 */
fun Float.inn(): Float {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_IN,
        this,
        Resources.getSystem().displayMetrics
    )
}


/**
 * int 转in
 */
fun Int.mm(): Int {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_MM,
        this.toFloat(),
        Resources.getSystem().displayMetrics
    ).toInt()
}

/**
 * Float 转in
 */
fun Float.mm(): Float {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_MM,
        this,
        Resources.getSystem().displayMetrics
    )
}

