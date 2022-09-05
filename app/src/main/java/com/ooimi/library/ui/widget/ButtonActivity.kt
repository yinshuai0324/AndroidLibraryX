package com.ooimi.library.ui.widget

import activity.BaseActivity
import com.ooimi.library.databinding.ActivityButtonBinding
import com.ooimi.base.viewmodel.BaseViewModel

class ButtonActivity : BaseActivity<BaseViewModel, ActivityButtonBinding>() {
    override fun initData() {
        title = "按钮控件"
    }

    override fun createdObserve() {
    }
}