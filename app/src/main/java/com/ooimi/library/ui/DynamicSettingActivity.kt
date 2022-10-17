package com.ooimi.library.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.SeekBar
import androidx.core.content.ContextCompat
import com.duodian.common.expand.dp
import com.ooimi.base.activity.BaseActivity
import com.ooimi.base.viewmodel.BaseViewModel
import com.ooimi.library.R
import com.ooimi.library.databinding.ActivityDynamicSettingBinding

class DynamicSettingActivity : BaseActivity<BaseViewModel, ActivityDynamicSettingBinding>() {
    companion object {
        fun jump(context: Context) {
            val intent = Intent(context, DynamicSettingActivity::class.java)
            context.startActivity(intent)
        }
    }

    private val bgBorderColor = R.color.color_45B97C
    override fun initData() {
        addViewClicks(R.id.setBg)
        viewBinding.roundImageView.setBorderColor(bgBorderColor)
        viewBinding.appButton.setBorderColor(bgBorderColor)
        viewBinding.roundLayout.setBorderColor(bgBorderColor)
    }

    override fun createdObserve() {
        viewBinding.borderBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                Log.i("===>>>","borderBar:${p1}")
                viewBinding.appButton.setBorderWidth((p1.toFloat()).dp)
                viewBinding.roundImageView.setBorderWidth((p1.toFloat()).dp)
                viewBinding.roundLayout.setBorderWidth((p1.toFloat()).dp)
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {

            }
        })

        viewBinding.roundBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                Log.i("===>>>","roundBar:${p1}")
                if (p1 <= 20) {
                    viewBinding.appButton.setRadius((p1.toFloat()).dp)
                }
                viewBinding.roundImageView.setRadius((p1.toFloat()).dp)
                viewBinding.roundLayout.setRadius((p1.toFloat()).dp)
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {

            }
        })
    }

    override fun onViewClick(view: View) {
        when (view.id) {
            R.id.setBg -> {
                viewBinding.appButton.setBackgroundColors(R.color.c_FF8B50)
                viewBinding.roundLayout.setBackgroundColors(R.color.c_FF8B50)
            }
        }
    }
}