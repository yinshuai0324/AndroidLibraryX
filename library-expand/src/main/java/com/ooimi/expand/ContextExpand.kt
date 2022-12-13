package com.ooimi.expand

import android.content.Context
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat

/**
 * 上下文相关
 */

/**
 * 获取颜色
 */
fun Context.getColors(@ColorRes resId: Int): Int {
    return ContextCompat.getColor(this, resId)
}






