package com.ooimi.base.pagestatus.state

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import com.ooimi.base.BaseLibrary
import com.ooimi.base.pagestatus.MultiState
import com.ooimi.base.pagestatus.MultiStateContainer
import com.ooimi.base.pagestatus.MultiStatePage
import com.ooimi.base.R

/**
 * 创建者：yinshuai
 * 创建时间：2021/7/1 13:52
 * 作用描述：空状态 带重试按钮
 */
class EmptyRetryState : MultiState() {
    private lateinit var tvEmptyMsg: TextView
    private lateinit var imgEmpty: ImageView
    private lateinit var retryBtn: Button
    private lateinit var rootView: View

    override fun onCreateMultiStateView(
        context: Context,
        inflater: LayoutInflater,
        container: MultiStateContainer
    ): View {
        return inflater.inflate(
            BaseLibrary.config?.pageStatusModelImp?.getEmptyRetryStateRes()
                ?: R.layout.mult_state_empty_retry, container, false
        )
    }

    override fun onMultiStateViewCreate(view: View) {
        rootView = view
        if (BaseLibrary.config?.pageStatusModelImp != null) {
            BaseLibrary.config?.pageStatusModelImp?.initEmptyRetryStateView(
                view,
                MultiStatePage.config
            )
        } else {
            tvEmptyMsg = view.findViewById(R.id.tv_empty_msg)
            imgEmpty = view.findViewById(R.id.img_empty)
            retryBtn = view.findViewById(R.id.retryBtn)
            setEmptyMsg(MultiStatePage.config.emptyMsg)
            setEmptyIcon(MultiStatePage.config.emptyIcon)
        }
    }

    override fun enableReload(): Boolean {
        return true
    }

    override fun bindRetryView(): View {
        return BaseLibrary.config?.pageStatusModelImp?.getEmptyRetryView(rootView) ?: retryBtn
    }

    private fun setEmptyMsg(emptyMsg: String) {
        tvEmptyMsg.text = emptyMsg
    }

    private fun setEmptyIcon(@DrawableRes emptyIcon: Int) {
        imgEmpty.setImageResource(emptyIcon)
    }
}