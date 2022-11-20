package com.ooimi.network.data

import android.text.TextUtils
import com.ooimi.network.NetworkLibrary

/**
 * @类作用描述:Response基类
 * @作者: 尹帅
 * @创建时间: 2022-09-06 12:35
 */
class ResponseBean<T> {
    //数据体
    var data: T? = null
    var body: T? = null

    //code
    var code: String? = ""
    var status: String? = ""

    //message
    var msg: String? = ""
    var message: String? = ""
    var desc: String? = ""

    //时间戳
    var time: Long? = 0
    var timestamp: Long? = 0


    /**
     * 获取数据内容
     */
    fun getRequestData(): T? {
        if (data != null) {
            return data
        }
        if (body != null) {
            return body
        }
        return null
    }

    /**
     * 获取状态码 String
     */
    fun getRequestCodeAsString(): String {
        if (!TextUtils.isEmpty(code)) {
            return code ?: ""
        }
        if (!TextUtils.isEmpty(status)){
            return status?:""
        }
        return "-1"
    }

    /**
     * 获取状态码 String
     */
    fun getRequestCodeAsInt(): Int {
        return try {
            if (!TextUtils.isEmpty(code)) {
                return (code ?: "0").toInt()
            }
            if (!TextUtils.isEmpty(status)){
                return (status ?: "0").toInt()
            }
            return -1
        } catch (e: Exception) {
            e.printStackTrace()
            -1
        }
    }

    /**
     * 获取请求的msg
     */
    fun getRequestMessage(): String {
        if (!TextUtils.isEmpty(msg)) {
            return msg ?: ""
        }
        if (!TextUtils.isEmpty(message)) {
            return message ?: ""
        }
        if (!TextUtils.isEmpty(desc)) {
            return desc ?: ""
        }
        return ""
    }

    /**
     * 获取请求时间戳
     */
    fun getRequestTime(): Long {
        if ((time ?: 0) > 0) {
            return time ?: 0
        }
        if ((timestamp ?: 0) > 0) {
            return timestamp ?: 0
        }
        return 0
    }

    fun isSuccess() = (getRequestCodeAsString() == NetworkLibrary.getConfig().requestSucceedCode)
}