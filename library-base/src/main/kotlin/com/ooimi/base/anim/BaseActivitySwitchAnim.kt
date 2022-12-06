package com.ooimi.base.anim

interface BaseActivitySwitchAnim {
    /**
     * 获取进入时即将启动的Activity的动画
     */
    fun getEnterLaunchAnim(): Int

    /**
     * 获取进入时当前Activity的退出动画
     */
    fun getEnterExitAnim(): Int

    /**
     * 获取返回时即将进入的Activity动画
     */
    fun getBackLaunchAnim(): Int

    /**
     * 获取返回时当前Activity退出的动画
     */
    fun getBackExitAnim(): Int

}