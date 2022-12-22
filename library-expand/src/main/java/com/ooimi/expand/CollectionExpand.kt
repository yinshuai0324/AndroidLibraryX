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

/**
 * 安全获取List中的前面几个
 */
fun <T> List<T>.safeGetFront(size: Int): List<T> {
    return try {
        if (this.size > size) {
            return this.subList(0, size - 1)
        } else if (this.size == size) {
            return this
        } else {
            return this.subList(0, this.size - 1)
        }
    } catch (e: Exception) {
        e.printStackTrace()
        arrayListOf()
    }
}