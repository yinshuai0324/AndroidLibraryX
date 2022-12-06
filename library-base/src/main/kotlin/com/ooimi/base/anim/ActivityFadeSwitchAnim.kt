package com.ooimi.base.anim

import com.ooimi.base.R

class ActivityFadeSwitchAnim : BaseActivitySwitchAnim {
    override fun getEnterLaunchAnim(): Int = R.anim.fade_in

    override fun getEnterExitAnim(): Int = R.anim.fade_out

    override fun getBackLaunchAnim(): Int = R.anim.fade_in

    override fun getBackExitAnim(): Int = R.anim.fade_out
}