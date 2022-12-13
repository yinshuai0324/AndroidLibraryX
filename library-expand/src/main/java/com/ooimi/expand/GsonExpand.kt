package com.ooimi.expand

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken

/**
 * 对象转Json字符串
 */
fun Any?.toJson(escape: Boolean = true): String {
    return try {
        if (escape) Gson().toJson(this) else GsonBuilder().disableHtmlEscaping().create()
            .toJson(this)
    } catch (e: Exception) {
        e.printStackTrace()
        ""
    }
}

/**
 * 字符串转对象
 */
fun <T> String?.fromJson(clazz: Class<T>): T? {
    return try {
        Gson().fromJson(this, clazz)
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}

/**
 * 字符串转List
 */
inline fun <reified T> String?.fromJsonList(): List<T> {
    return try {
        val type = object : TypeToken<List<T>>() {}.type
        Gson().fromJson(this, type)
    } catch (e: Exception) {
        e.printStackTrace()
        arrayListOf()
    }
}