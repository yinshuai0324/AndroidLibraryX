package com.ooimi.library.ui.widget

import com.ooimi.base.activity.BaseActivity
import com.ooimi.library.databinding.ActivityLayoutBinding
import com.ooimi.base.viewmodel.BaseViewModel

class LayoutActivity : BaseActivity<BaseViewModel, ActivityLayoutBinding>() {

    override fun initData() {
        title = "容器控件"
    }

    override fun createdObserve() {
    }
}