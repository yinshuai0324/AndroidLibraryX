package com.ooimi.expand

import android.graphics.Color
import android.text.Spanned
import androidx.core.text.HtmlCompat

/**
 * 字符串扩展方法
 */


/**
 * 字符串转颜色
 */
fun String.toColor(default: Int = Color.WHITE): Int {
    return try {
        Color.parseColor(this)
    } catch (e: Exception) {
        e.printStackTrace()
        default
    }
}

/**
 * 解析Html
 */
fun String.toHtml(): Spanned {
    return HtmlCompat.fromHtml(this, HtmlCompat.FROM_HTML_MODE_COMPACT)
}

/**
 * 如果为空 则返回空字符串
 */
fun String?.nullAsBlank(): String {
    return this ?: ""
}



