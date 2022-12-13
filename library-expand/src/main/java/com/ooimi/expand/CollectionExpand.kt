package com.ooimi.expand

/**
 * 集合相关
 */

/**
 * 安全获取List中的元素
 */
fun <T> List<T>.safeGet(index: Int): T? {
    return try {
        if (index >= this.size) {
            null
        } else {
            this[index]
        }
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}