package com.ooimi.base.pagestatus

/**
 * @ProjectName: MultiStatePage
 * @Description: TODO
 * @CreateDate: 2020/11/4 16:04
 */
fun interface OnNotifyListener<T : MultiState> {
    fun onNotify(multiState: T)
}