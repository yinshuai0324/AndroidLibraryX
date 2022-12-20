package com.ooimi.network.request

import com.ooimi.network.NetworkLibrary
import com.ooimi.network.code.HttpCode
import com.ooimi.network.dsl.NetworkRequestDsl
import com.ooimi.network.exception.ApiRequestException
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
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
    private val config = NetworkLibrary.getConfig()

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
    @JvmStatic
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
        val requestDsl = NetworkRequestDsl<T>()
        requestDsl.dsl()

        flow {
            //请求数据 子线程
            val response = requestDsl.api?.invoke()
            response?.let {
                //存一下服务端返回的描述
                requestDsl.message = it.getMessage()
                //回掉整个请求结果
                launchUi(scope) {
                    //处理请求结果
                    config.requestResultHandler?.onData(it, requestDsl.isShowToast)
                }
                //处理数据
                if (it.isSucceed()) {
                    //对数据进行返回前的处理 子线程
                    val data = if (requestDsl.onBeforeHandler != null) {
                        requestDsl.onBeforeHandler?.invoke(it.getBody())
                    } else {
                        it.getBody()
                    }
                    //检查是否自定义处理
                    if (requestDsl.onCustomHandler != null && requestDsl.onCustomHandlerComplete != null) {
                        val customHandlerResult = requestDsl.onCustomHandler?.invoke(data)
                        //回掉自定义处理结果
                        customHandlerResult?.let {
                            launchUi(scope) {
                                requestDsl.onCustomHandlerComplete?.invoke(it)
                            }
                        }
                    }
                    emit(data)
                } else {
                    //请求失败
                    throw ApiRequestException(it.getCode(), it.getMessage() ?: "")
                }
            }
        }.flowOn(Dispatchers.IO).onStart {
            //显示加载框 ui线程
            requestDsl.onLoading?.invoke()
        }.onCompletion {
            //请求完成回调 ui线程
            requestDsl.onComplete?.invoke()
            requestDsl.onHideLoading?.invoke()
        }.onEach {
            //请求成功 ui线程
            if (it != null) {
                //请求成功 但是data不为空
                requestDsl.onSuccess?.invoke(it)
            } else {
                //请求成功 但是data为空
                requestDsl.onSuccessEmpty?.invoke()
            }
            //请求成功 data是不是为空需要自己判断
            requestDsl.onSuccessEmptyData?.invoke(it)
        }.onEmpty {
            //请求没有任何数据 ui线程 没有实际的应用场景 暂时不处理
        }.catch { exception ->
            if (NetworkLibrary.getConfig().isOpenLog) {
                exception.printStackTrace()
            }
            if (exception is ApiRequestException) {
                config.requestResultHandler?.onBusinessException(exception)
            } else {
                //异常时回调
                config.requestResultHandler?.onException(exception)
            }
            //发生异常时回掉 ui线程
            when (exception) {
                is ApiRequestException -> {
                    requestDsl.onFailed?.invoke(exception.msg, exception.code)
                }
                is UnknownHostException -> {
                    requestDsl.onFailed?.invoke("网络异常，请检查网络", HttpCode.HTTP_CODE_FAILURE)
                }
                is ConnectException -> {
                    requestDsl.onFailed?.invoke("网络异常，请检查网络", HttpCode.HTTP_CODE_FAILURE)
                }
                else -> requestDsl.onFailed?.invoke(
                    "请求失败:${exception.message}",
                    HttpCode.HTTP_CODE_FAILURE
                )
            }
        }.flowOn(Dispatchers.Main).launchIn(scope)
    }

    private fun launchUi(scope: CoroutineScope, block: () -> Unit) {
        scope.launch(Dispatchers.Main) { block.invoke() }
    }

    /**
     * 获取默认的ApiService
     */
    @JvmStatic
    fun <T> getDefaultApiService(api: Class<T>): T {
        return NetworkLibrary.getDefaultRetrofitInstance().create(api)
    }

    /**
     * 获取默认的协程作用域
     */
    @JvmStatic
    fun getDefaultScope(): CoroutineScope {
        return defaultScope
    }

    /**
     * 获取默认的ApiService
     */
    @JvmStatic
    fun <T> getApiService(key: String, api: Class<T>): T {
        if (NetworkLibrary.retrofitInstance.containsKey(key) && NetworkLibrary.retrofitInstance[key] != null) {
            return NetworkLibrary.retrofitInstance[key]!!.create(api)
        } else {
            throw Exception("获取ApiService时异常，未找到key:${key}对应的Retrofit实例")
        }
    }
}