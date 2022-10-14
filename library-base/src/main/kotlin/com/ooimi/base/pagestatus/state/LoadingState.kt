package com.ooimi.base.pagestatus.state

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.ooimi.base.BaseLibrary
import com.ooimi.base.R
import com.ooimi.base.pagestatus.MultiState
import com.ooimi.base.pagestatus.MultiStateContainer
import com.ooimi.base.pagestatus.MultiStatePage

/**
 * 创建者：yinshuai
 * 创建时间：2021/7/1 13:52
 * 作用描述：加载中状态
 */
class LoadingState : MultiState() {
    private lateinit var tvLoadingMsg: TextView
    override fun onCreateMultiStateView(
        context: Context,
        inflater: LayoutInflater,
        container: MultiStateContainer
    ): View {
        return inflater.inflate(
            BaseLibrary.config?.pageStatusModelImp?.getLoadingStateRes()
                ?: R.layout.mult_state_loading, container, false
        )
    }

    override fun onMultiStateViewCreate(view: View) {
        if (BaseLibrary.config?.pageStatusModelImp != null) {
            BaseLibrary.config?.pageStatusModelImp?.initLoadingStateView(view, MultiStatePage.config)
        } else {
            tvLoadingMsg = view.findViewById(R.id.tv_loading_msg)
            setLoadingMsg(MultiStatePage.config.loadingMsg)
        }
    }

    private fun setLoadingMsg(loadingMsg: String) {
        tvLoadingMsg.text = loadingMsg
    }
}