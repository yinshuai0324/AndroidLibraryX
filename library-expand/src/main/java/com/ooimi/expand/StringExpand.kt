package com.ooimi.expand

import android.graphics.Color

/**
 * 字符串扩展方法
 */


/**
 * 字符串转颜色
 */
fun String.toColor(): Int {
    return try {
        Color.parseColor(this)
    } catch (e: Exception) {
        e.printStackTrace()
        Color.WHITE
    }
}

