package com.ooimi.base

import com.ooimi.base.imp.LoadingModelImp
import com.ooimi.base.imp.ToastModelImp

/**
 * @类作用描述:Base框架参数配置
 * @作者: 尹帅
 * @创建时间: 2022-10-13 19:59
 */
class BaseLibraryBuilder {
    /**
     * ActivityLoading 弹窗样式自定义
     */
    internal var loadingModelImp: LoadingModelImp? = null

    /**
     * ActivityToast 样式自定义
     */
    internal var toastModelImp: ToastModelImp? = null


    fun setLoadingModelImp(imp: LoadingModelImp): BaseLibraryBuilder {
        this.loadingModelImp = imp
        return this
    }

    fun setToastModelImp(imp: ToastModelImp): BaseLibraryBuilder {
        this.toastModelImp = imp
        return this
    }

    fun init() = BaseLibrary.init(this)
}