package com.ooimi.local.expand

import com.google.gson.Gson
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class ParameterizedTypeImpl(val clz: Class<*>) : ParameterizedType {
    override fun getRawType(): Type = List::class.java

    override fun getOwnerType(): Type? = null

    override fun getActualTypeArguments(): Array<Type> = arrayOf(clz)
}

//重点
inline fun <reified T> String.toBeanList(): List<T> = Gson().fromJson<List<T>>(this, ParameterizedTypeImpl(T::class.java))
fun Any.toJson(): String = Gson().toJson(this)