package com.ooimi.base.pagestatus.state

import android.content.Context
import android.view.LayoutInflater
import android.view.View
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
 * 作用描述：空状态
 */
class EmptyState : MultiState() {

    private lateinit var tvEmptyMsg: TextView
    private lateinit var imgEmpty: ImageView
    override fun onCreateMultiStateView(
        context: Context,
        inflater: LayoutInflater,
        container: MultiStateContainer
    ): View {
        return inflater.inflate(
            BaseLibrary.config?.pageStatusModelImp?.getEmptyStateRes()
                ?: R.layout.mult_state_empty, container, false
        )
    }

    override fun onMultiStateViewCreate(view: View) {
        if (BaseLibrary.config?.pageStatusModelImp != null) {
            BaseLibrary.config?.pageStatusModelImp?.initEmptyStateView(view, MultiStatePage.config)
        } else {
            tvEmptyMsg = view.findViewById(R.id.tv_empty_msg)
            imgEmpty = view.findViewById(R.id.img_empty)
            setEmptyMsg(MultiStatePage.config.emptyMsg)
            setEmptyIcon(MultiStatePage.config.emptyIcon)
        }

    }

    private fun setEmptyMsg(emptyMsg: String) {
        tvEmptyMsg.text = emptyMsg
    }

    private fun setEmptyIcon(@DrawableRes emptyIcon: Int) {
        imgEmpty.setImageResource(emptyIcon)
    }
}