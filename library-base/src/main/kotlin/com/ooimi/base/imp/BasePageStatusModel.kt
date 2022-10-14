package com.ooimi.base.imp

import android.view.View
import com.ooimi.base.pagestatus.MultiStateConfig

/**
 * @接口作用描述:自定义Activity、Fragment 缺省页
 * @作者: 尹帅
 * @创建时间: 2022-10-14 11:32
 */
interface BasePageStatusModel {
    /**
     * 获取错误时的提示
     */
    fun getErrorMessage(): String

    /**
     * 获取网络错误时的提示
     */
    fun getNetworkErrorMessage(): String

    /**
     * 获取空数据时的提示
     */
    fun getEmptyMessage(): String

    /**
     * 获取加载中的提示
     */
    fun getLoadingMessage(): String

    /**
     * 获取错误时的Icon
     */
    fun getErrorIcon(): Int

    /**
     * 获取网络错误时的Icon
     */
    fun getNetworkErrorIcon(): Int

    /**
     * 获取空数据时的Icon
     */
    fun getEmptyIcon(): Int

    /**
     * 获取空数据-带重试时的layout res
     */
    fun getEmptyRetryStateRes(): Int

    /**
     * 获取空数据时的layout res
     */
    fun getEmptyStateRes(): Int

    /**
     * 获取错误-带重试时的layout res
     */
    fun getErrorRetryStateRes(): Int

    /**
     * 获取错误时的layout res
     */
    fun getErrorStateRes(): Int

    /**
     * 获取加载中的layout res
     */
    fun getLoadingStateRes(): Int

    /**
     * 获取网络错误-带重试的layout res
     */
    fun getNetworkErrorRetryStateRes(): Int

    /**
     * 获取网络错误的layout res
     */
    fun getNetworkErrorStateRes(): Int

    /**
     * 空数据-带重试的View创建时回掉
     */
    fun initEmptyRetryStateView(rootView: View, config: MultiStateConfig)

    /**
     * 空数据-带重试的重试按钮
     */
    fun getEmptyRetryView(rootView: View): View

    /**
     * 空数据的View创建时回掉
     */
    fun initEmptyStateView(rootView: View, config: MultiStateConfig)

    /**
     * 错误时-带重试 的View创建时回掉
     */
    fun initErrorRetryStateView(rootView: View, config: MultiStateConfig)

    /**
     * 错误时的重试按钮
     */
    fun getErrorRetryView(rootView: View): View

    /**
     * 错误时的View创建时回掉
     */
    fun initErrorStateView(rootView: View, config: MultiStateConfig)

    /**
     * 加载中时的View创建时回掉
     */
    fun initLoadingStateView(rootView: View, config: MultiStateConfig)

    /**
     * 网络错误时-带重试 的View创建时回掉
     */
    fun initNetworkErrorRetryStateView(rootView: View, config: MultiStateConfig)

    /**
     * 网络错误时-带重试 的重试按钮
     */
    fun getNetworkErrorRetryView(rootView: View): View

    /**
     * 网络错误时 的View创建时回掉
     */
    fun initNetworkErrorStateView(rootView: View, config: MultiStateConfig)
}