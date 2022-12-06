package com.ooimi.base.utils

import android.app.Activity
import com.ooimi.base.BaseLibrary

class ActivityAnimUtils {

    companion object {
        /**
         * 打开新页面时的动画
         */
        fun onOpen(context: Activity) {
            BaseLibrary.config?.activitySwitchAnim?.let {
                context.overridePendingTransition(it.getEnterLaunchAnim(), it.getEnterExitAnim())
            }
        }

        /**
         * 返回时的动画
         */
        fun onBack(context: Activity) {
            BaseLibrary.config?.activitySwitchAnim?.let {
                context.overridePendingTransition(it.getBackLaunchAnim(), it.getBackExitAnim())
            }
        }
    }

}