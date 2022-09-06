package com.ooimi.network.config

/**
 * @类作用描述:
 * @作者: 尹帅
 * @创建时间: 2022-09-06 12:07
 */
class ResponseConfig {
    /**
     * Response基类
     */
    var clazz: Class<Any>? = null

    /**
     * code字段
     */
    var codeField: String? = ""

    /**
     * data字段
     */
    var dataField: String? = ""

    /**
     * 描述字段
     */
    var msgField: String? = ""
}