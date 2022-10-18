package com.ooimi.network.exception

/**
 * @类作用描述:
 * @作者: 尹帅
 * @创建时间: 2022-10-18 11:41
 */
class ApiRequestException constructor(val code: Int, val msg: String) : Exception(msg)