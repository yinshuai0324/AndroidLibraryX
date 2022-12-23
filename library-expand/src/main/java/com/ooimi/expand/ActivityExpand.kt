package com.ooimi.expand

import android.animation.ObjectAnimator
import android.app.Activity
import android.os.Build
import android.view.View
import androidx.core.animation.doOnEnd

/**
 * Activity的扩展类
 */

/**
 * 设置Android12启动页的退出动画-渐变
 */
fun Activity.applyExitAlphaAnim(duration: Long) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        splashScreen.setOnExitAnimationListener { splashScreenView ->
            val slideUp = ObjectAnimator.ofFloat(splashScreenView, View.ALPHA, 1f, 0f)
            slideUp.duration = duration
            slideUp.doOnEnd {
                splashScreenView.remove()
            }
            slideUp.start()
        }
    }
}
