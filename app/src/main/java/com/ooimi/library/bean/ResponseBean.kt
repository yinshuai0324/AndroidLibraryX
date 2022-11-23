package com.ooimi.library.bean

import com.ooimi.network.data.BaseResponseBean

class ResponseBean<T> : BaseResponseBean<T>() {

    var code: String? = ""
    var desc: String? = ""
    var token: String? = ""
    var data: T? = null

    override fun isSucceed(): Boolean = code == "0"

    override fun getBody(): T? = data

    override fun getMessage(): String? = desc

    override fun getCode(): Int = (code ?: "-1").toInt()
}