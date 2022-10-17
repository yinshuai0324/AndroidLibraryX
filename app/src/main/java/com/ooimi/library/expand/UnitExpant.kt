package com.duodian.common.expand

import android.content.res.Resources

/**
 * @作用描述:单位转换扩展函数
 * @作者: 尹帅
 * @创建时间: 2021-11-09 15:41
 */

val Float.dp: Float
    get() = android.util.TypedValue.applyDimension(
        android.util.TypedValue.COMPLEX_UNIT_DIP, this, Resources.getSystem().displayMetrics
    )

val Int.dp: Int
    get() = android.util.TypedValue.applyDimension(
        android.util.TypedValue.COMPLEX_UNIT_DIP,
        this.toFloat(),
        Resources.getSystem().displayMetrics
    ).toInt()


