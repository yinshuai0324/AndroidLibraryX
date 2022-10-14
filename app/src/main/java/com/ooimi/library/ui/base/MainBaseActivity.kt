package com.ooimi.library.ui.base

import android.view.View
import com.ooimi.base.activity.BaseActivity
import com.ooimi.base.viewmodel.BaseViewModel
import com.ooimi.library.R
import com.ooimi.library.databinding.ActivityMainBaseBinding
import com.ooimi.library.databinding.ActivityMainBinding

class MainBaseActivity : BaseActivity<BaseViewModel, ActivityMainBaseBinding>() {
    override fun initData() {
        addViewClicks(R.id.showLoading,R.id.dismissLoading)
    }

    override fun createdObserve() {

    }

    override fun onViewClick(view: View) {
        when (view.id) {
            R.id.showLoading -> {
                showLoading("加载中...")
            }
            R.id.dismissLoading -> {
                dismissLoading()
            }
        }
    }

}