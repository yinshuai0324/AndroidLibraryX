package com.ooimi.expand

import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.NumberFormat

/**
 * 数字扩展方法
 */

private val DF_THREAD_LOCAL = object : ThreadLocal<DecimalFormat>() {
    override fun initialValue(): DecimalFormat {
        return NumberFormat.getInstance() as DecimalFormat
    }
}

/**
 * 格式化
 * @param fractionDigits 保留多少位有效小数
 */
fun Double?.format(fractionDigits: Int): Double {
    return format(false, 0, fractionDigits, false)
}

/**
 * 格式化 不保留小数
 */
fun Double?.format(): Double {
    return format(false, 0, 0, false)
}

/**
 * 格式化
 * @param isGrouping 是否最字符串进行，分割 比如100,999,999,000
 * @param groupingSize 相隔多少位用，分割
 * @param fractionDigits 保留多少位有效小数
 * @param isHalfUp 四舍五入是否向上取整 否则向下
 */
fun Double?.format(
    isGrouping: Boolean = false,
    groupingSize: Int = 0,
    fractionDigits: Int,
    isHalfUp: Boolean = false
): Double {
    val df = DF_THREAD_LOCAL.get()
    df?.isGroupingUsed = isGrouping
    df?.groupingSize = groupingSize
    df?.minimumFractionDigits = 0
    df?.maximumFractionDigits = fractionDigits
    df?.roundingMode = if (isHalfUp) RoundingMode.HALF_UP else RoundingMode.HALF_DOWN
    return df?.format(this)?.toDouble() ?: 0.0
}