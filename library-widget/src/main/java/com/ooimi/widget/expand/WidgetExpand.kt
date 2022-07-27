package com.ooimi.widget.expand

/**
 * @author 尹帅
 * @date 2022/7/26 15:03
 * @desc
 */


/**
 * 颜色加透明度
 */
internal fun Int.toColorAlpha(alpha: Float): Int {
    val temp = 255.coerceAtMost(0.coerceAtLeast((alpha * 255).toInt())) shl 24
    val rgb = 0x00ffffff and this
    return temp + rgb
}