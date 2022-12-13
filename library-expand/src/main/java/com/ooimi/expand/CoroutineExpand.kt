package com.ooimi.expand

import kotlinx.coroutines.CancellableContinuation
import kotlin.coroutines.resume

/**
 * 协程相关
 */

/**
 * 在异步转同步时安全回调结果
 */
fun <T> CancellableContinuation<T>.safeResume(value: T) {
    if (this.isActive) {
        try {
            this.resume(value)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}