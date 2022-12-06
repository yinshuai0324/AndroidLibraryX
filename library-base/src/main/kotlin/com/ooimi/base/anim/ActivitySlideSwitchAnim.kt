package com.ooimi.base.anim

import com.ooimi.base.R

class ActivitySlideSwitchAnim : BaseActivitySwitchAnim {
    override fun getEnterLaunchAnim(): Int = R.anim.slide_in_right

    override fun getEnterExitAnim(): Int = R.anim.slide_out_left

    override fun getBackLaunchAnim(): Int = R.anim.slide_in_left

    override fun getBackExitAnim(): Int = R.anim.slide_out_right
}