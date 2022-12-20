package com.ooimi.expand

/**
 * 安全执行代码
 */
fun safetyExecute(block: () -> Unit) {
    try {
        block.invoke()
    } catch (e: Exception) {
        e.printStackTrace()
    }
}