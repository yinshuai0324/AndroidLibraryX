package com.ooimi.base.expand

import java.lang.reflect.ParameterizedType

/**
 * @作用描述:
 * @作者: 尹帅
 * @创建时间: 2022-09-05 16:15
 */

/**
 * 获取当前类绑定的泛型ViewModel-clazz
 */
@Suppress("UNCHECKED_CAST")
fun <VM> getVmClazz(obj: Any): VM {
    return (obj.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as VM
}

/**
 * 安全执行代码
 */
fun safeExecute(block: () -> Unit) {
    try {
        block.invoke()
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun Boolean?.nullAsFalse(): Boolean {
    return this ?: false
}

fun Boolean?.nullAsTrue(): Boolean {
    return this ?: true
}

fun <T> T?.nullOr(value: T): T = this ?: value

fun String?.nullAsBlank(): String {
    return this ?: ""
}