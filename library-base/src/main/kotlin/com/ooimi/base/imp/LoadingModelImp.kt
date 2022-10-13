package com.ooimi.base.imp

import android.view.View

/**
 * @接口作用描述:
 * @作者: 尹帅
 * @创建时间: 2022-10-13 20:00
 */
interface LoadingModelImp {
    /**
     * 加载框的布局文件
     */
    fun getLayoutResId(): Int

    /**
     * 初始化数据
     */
    fun initialize(view: View, msg: String)

    /**
     * 是否按返回键可以关闭
     */
    fun isCancelableDismiss(): Boolean

    /**
     * 是否点击弹窗以外的区域可以关闭
     */
    fun isTouchOutsideDismiss(): Boolean
}