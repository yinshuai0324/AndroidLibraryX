package com.ooimi.library.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.ooimi.base.activity.BaseActivity
import com.ooimi.base.viewmodel.BaseViewModel
import com.ooimi.library.R
import com.ooimi.library.databinding.ActivityDebugBinding

class DebugActivity : BaseActivity<BaseViewModel, ActivityDebugBinding>() {

    override fun initData() {
        addViewClicks(R.id.setBg, R.id.setRound, R.id.setBorder)
    }

    override fun createdObserve() {
        TODO("Not yet implemented")
    }

    override fun onViewClick(view: View) {
        when (view.id) {
            R.id.setBg -> {

            }
            R.id.setRound -> {

            }
            R.id.setBorder -> {

            }
        }
    }
}