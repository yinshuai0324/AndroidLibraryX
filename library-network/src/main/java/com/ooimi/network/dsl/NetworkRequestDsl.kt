package com.ooimi.network.dsl

import com.ooimi.network.data.BaseResponseBean


/**
 * 网络请求的DSL
 */
class NetworkRequestDsl<T> {
    var api: (suspend () -> BaseResponseBean<T>)? = null

    internal var onLoading: (() -> Unit)? = null
        private set
    internal var onBeforeHandler: (suspend (T?) -> T?)? = null
        private set
    internal var onSuccess: ((T) -> Unit)? = null
        private set
    internal var onSuccessEmpty: (() -> Unit)? = null
        private set
    internal var onSuccessEmptyData: ((T?) -> Unit)? = null
        private set
    internal var onComplete: (() -> Unit)? = null
        private set
    internal var onFailed: ((error: String?, code: Int?) -> Unit)? = null
        private set
    internal var onHideLoading: (() -> Unit)? = null
        private set
    internal var onCustomHandler: ((T?) -> Any?)? = null
        private set
    internal var onCustomHandlerComplete: ((Any?) -> Unit)? = null
        private set

    /**
     * 基础数据
     */
    var totalRecord: Int = 0
    var timestamp: Long = 0
    var message: String? = ""
    var isShowToast: Boolean = true


    internal fun clean() {
        onSuccess = null
        onComplete = null
        onFailed = null
        onLoading = null
        onSuccessEmpty = null
        onHideLoading = null
        onCustomHandler = null
        onCustomHandlerComplete = null
    }

    fun onLoading(block: () -> Unit) {
        this.onLoading = block
    }

    fun onHideLoading(block: () -> Unit) {
        this.onHideLoading = block
    }

    fun onBeforeHandler(block: suspend (T?) -> T?) {
        this.onBeforeHandler = block
    }

    fun onSuccess(block: (T) -> Unit) {
        this.onSuccess = block
    }

    fun onSuccessEmpty(block: () -> Unit) {
        this.onSuccessEmpty = block
    }

    fun onSuccessEmptyData(block: (T?) -> Unit) {
        this.onSuccessEmptyData = block
    }

    fun onComplete(block: () -> Unit) {
        this.onComplete = block
    }

    fun onFailed(block: (errorMsg: String?, code: Int?) -> Unit) {
        this.onFailed = block
    }

    fun onCustomHandler(block: ((T?) -> Any?)) {
        this.onCustomHandler = block
    }

    fun onCustomHandlerComplete(block: ((Any?) -> Unit)) {
        this.onCustomHandlerComplete = block
    }


}