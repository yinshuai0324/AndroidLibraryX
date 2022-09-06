package com.ooimi.network.request

import com.ooimi.network.NetworkManage
import com.ooimi.network.code.HttpCode
import com.ooimi.network.dsl.NetworkRequestDsl
import kotlinx.coroutines.*
import java.net.ConnectException
import java.net.UnknownHostException

/**
 * 创建者：yinshuai
 * 创建时间：2021/7/3 09:11
 * 作用描述：请求封装
 */
object ApiRequest {
    /**
     * 默认的作用域
     */
    private val defaultScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    /**
     * 配置信息
     */
    private val config = NetworkManage.getConfig()

    /**
     * Api请求 不处理异常 直接返回数据 需要自己捕获异常
     */
    suspend fun <T> apiRequestAwait(block: suspend () -> T?): T? {
        return block.invoke()
    }


    /**
     * api同步请求 处理异常
     */
    suspend fun <T> safeApiRequestAwait(api: suspend () -> T): T? {
        return try {
            api.invoke()
        } catch (e: Exception) {
            null
        } finally {
        }
    }


    /**
     * Api请求 安全调用 处理好请求时可能发生的异常
     * @param dsl 网络请求dsl
     */
    fun <T> safeApiRequest(
        dsl: NetworkRequestDsl<T>.() -> Unit
    ) {
        defaultScope.launch {
            safeApiRequest(this, dsl)
        }
    }


    /**
     * Api请求 安全调用 处理好请求时可能发生的异常
     * @param dsl 网络请求dsl
     * @param scope 协程作用域
     */
    suspend fun <T> safeApiRequest(
        scope: CoroutineScope,
        dsl: NetworkRequestDsl<T>.() -> Unit
    ) {
        val retrofitCoroutine = NetworkRequestDsl<T>()
        retrofitCoroutine.dsl()
        //显示加载框
        retrofitCoroutine.onLoading?.invoke()
        retrofitCoroutine.api.let {
            try {
                val response = withContext(scope.coroutineContext + Dispatchers.IO) { it?.invoke() }
                response?.let {
                    //存一下服务端返回的描述
                    retrofitCoroutine.message = it.getRequestMessage()
                    //请求完成回调
                    retrofitCoroutine.onComplete?.invoke()
                    //处理状态码
                    if (it.getRequestCodeAsString() == config.requestSucceedCode) {
                        //对数据进行返回前的处理 子线程
                        val data = if (retrofitCoroutine.onBeforeHandler != null) {
                            retrofitCoroutine.onBeforeHandler?.invoke(it.getRequestData())
                        } else {
                            it.getRequestData()
                        }
                        //请求成功
                        if (data != null) {
                            //请求成功 但是data不为空
                            retrofitCoroutine.onSuccess?.invoke(data)
                        } else {
                            //请求成功 但是data为空
                            retrofitCoroutine.onSuccessEmpty?.invoke()
                        }
                        //请求成功 data是不是为空需要自己判断
                        retrofitCoroutine.onSuccessEmptyData?.invoke(data)
                    } else {
                        //请求失败
                        retrofitCoroutine.onFailed?.invoke(
                            it.getRequestMessage(),
                            it.getRequestCodeAsInt()
                        )
                    }
                    //处理请求结果
                    config.requestResultHandler?.onData(it)
                }
            } catch (e: UnknownHostException) {
                launchUi(scope) {
                    retrofitCoroutine.onFailed?.invoke("网络异常，请检查网络", HttpCode.HTTP_CODE_FAILURE)
                }
            } catch (e: ConnectException) {
                launchUi(scope) {
                    retrofitCoroutine.onFailed?.invoke("网络不稳定，请检查网络", HttpCode.HTTP_CODE_FAILURE)
                }
            } catch (e: Exception) {
                launchUi(scope) {
                    retrofitCoroutine.onFailed?.invoke(
                        "请求失败:${e.message}",
                        HttpCode.HTTP_CODE_FAILURE
                    )
                }
            } finally {
                launchUi(scope) {
                    retrofitCoroutine.onHideLoading?.invoke()
                }
            }
        }
    }

    private fun launchUi(scope: CoroutineScope, block: () -> Unit) {
        scope.launch(Dispatchers.Main) { block.invoke() }
    }
}