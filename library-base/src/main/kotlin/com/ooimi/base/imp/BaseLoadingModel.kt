package com.ooimi.base.imp

import android.view.View

/**
 * @接口作用描述:自定义Activity、Fragment Loading弹窗
 * @作者: 尹帅
 * @创建时间: 2022-10-13 20:00
 */
interface BaseLoadingModel {
    /**
     * 加载框的布局文件
     */
    fun getLayoutResId(): Int

    /**
     * 更新UI数据
     */
    fun updateUIData(rootView: View?, msg: String)

    /**
     * 是否按返回键可以关闭
     */
    fun isCancelableDismiss(): Boolean

    /**
     * 是否点击弹窗以外的区域可以关闭
     */
    fun isTouchOutsideDismiss(): Boolean
}