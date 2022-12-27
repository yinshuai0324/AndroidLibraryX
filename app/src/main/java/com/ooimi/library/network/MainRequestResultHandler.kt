package com.ooimi.library.network

import android.util.Log
import android.widget.Toast
import com.ooimi.network.data.BaseResponseBean
import com.ooimi.network.exception.ApiRequestException
import com.ooimi.network.handler.BaseRequestResultHandler

/**
 * @类作用描述:请求结果回掉
 * @作者: 尹帅
 * @创建时间: 2022-09-06 15:20
 */
class MainRequestResultHandler : BaseRequestResultHandler {


    override fun onData(data: BaseResponseBean<*>, isShowToast: Boolean) {
        Log.i("===>>>","onData:${data.toJsonString()}")
        when (data.getCode()) {
            0 -> {
                //成功
            }
            1 -> {
                //Token失效等等
            }
        }
    }

    override fun onException(throwable: Throwable) {
        Log.i("===>>>", "请求异常:${throwable}")
    }

    override fun onBusinessException(exception: ApiRequestException) {

    }
}