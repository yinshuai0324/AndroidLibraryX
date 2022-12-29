package com.ooimi.expand

/**
 * @author 尹帅
 * @Description Int扩展方法
 * @createTime 2022年12月28日 18:51
 */

/**
 * 如果当前小于9 则加0
 */
fun Int.addZero(): String {
    return if (this <= 9) "0${this}" else "$this"
}