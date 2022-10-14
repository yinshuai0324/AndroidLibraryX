package com.ooimi.base

import com.ooimi.base.imp.BaseLoadingModel
import com.ooimi.base.imp.BaseToastModel

/**
 * @类作用描述:Base框架参数配置
 * @作者: 尹帅
 * @创建时间: 2022-10-13 19:59
 */
class BaseLibraryBuilder {
    /**
     * ActivityLoading 弹窗样式自定义
     */
    internal var loadingModelImp: BaseLoadingModel? = null

    /**
     * ActivityToast 样式自定义
     */
    internal var toastModelImp: BaseToastModel? = null


    fun setLoadingImp(imp: BaseLoadingModel): BaseLibraryBuilder {
        this.loadingModelImp = imp
        return this
    }

    fun setToastImp(imp: BaseToastModel): BaseLibraryBuilder {
        this.toastModelImp = imp
        return this
    }

    fun init() = BaseLibrary.init(this)
}