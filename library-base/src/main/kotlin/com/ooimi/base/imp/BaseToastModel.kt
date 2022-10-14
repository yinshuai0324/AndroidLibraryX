package com.ooimi.base.imp

import android.content.Context

/**
 * @接口作用描述:自定义Activity、Fragment Toast
 * @作者: 尹帅
 * @创建时间: 2022-10-13 20:25
 */
interface BaseToastModel {

    fun toast(context: Context, msg: String)
}