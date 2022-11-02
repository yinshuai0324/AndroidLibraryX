package com.ooimi.local.imp

import android.content.Context
import android.text.TextUtils
import com.google.gson.Gson
import com.tencent.mmkv.MMKV


object LocalData {
    private lateinit var mmkv: MMKV

    @JvmStatic
    internal fun init(context: Context) {
        MMKV.initialize(context)
        mmkv = MMKV.mmkvWithID(context.packageName, MMKV.MULTI_PROCESS_MODE)
    }

    @JvmStatic
    fun put(key: String, value: Any?) {
        when (value) {
            is Int -> mmkv.encode(key, value)
            is Long -> mmkv.encode(key, value)
            is String -> mmkv.encode(key, value)
            is Double -> mmkv.encode(key, value)
            is Float -> mmkv.encode(key, value)
            is Boolean -> mmkv.encode(key, value)
            else -> {
                value?.let {
                    val json = Gson().toJson(it)
                    mmkv.encode(key, json)
                }
            }
        }
    }

    @JvmStatic
    fun getInt(key: String, default: Int = 0): Int {
        return mmkv.decodeInt(key, default)
    }

    @JvmStatic
    fun getString(key: String, default: String = ""): String {
        return mmkv.decodeString(key, default) ?: ""
    }

    @JvmStatic
    fun getLong(key: String, default: Long = 0): Long {
        return mmkv.decodeLong(key, default)
    }

    @JvmStatic
    fun getDouble(key: String, default: Double = 0.00): Double {
        return mmkv.decodeDouble(key, default)
    }

    @JvmStatic
    fun getFloat(key: String, default: Float = 0f): Float {
        return mmkv.decodeFloat(key, default)
    }

    @JvmStatic
    fun getBoolean(key: String, default: Boolean): Boolean {
        return mmkv.decodeBool(key, default)
    }

    @JvmStatic
    fun <T> getObj(key: String, clazz: Class<T>): T? {
        try {
            val json = mmkv.decodeString(key, "") ?: ""
            if (!TextUtils.isEmpty(json)) {
                return Gson().fromJson(json, clazz)
            }
        } catch (e: Exception) {
            return null
        }
        return null
    }

    @JvmStatic
    fun remove(key: String) {
        mmkv.remove(key)
    }
}