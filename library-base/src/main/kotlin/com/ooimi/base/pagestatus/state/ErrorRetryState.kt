package com.ooimi.base.pagestatus.state

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import com.ooimi.base.BaseLibrary
import com.ooimi.base.R
import com.ooimi.base.pagestatus.MultiState
import com.ooimi.base.pagestatus.MultiStateContainer
import com.ooimi.base.pagestatus.MultiStatePage

/**
 * 创建者：yinshuai
 * 创建时间：2021/7/1 13:52
 * 作用描述：错误状态
 */
class ErrorRetryState : MultiState() {

    private lateinit var tvErrorMsg: TextView
    private lateinit var imgError: ImageView
    private lateinit var tvRetry: Button
    private lateinit var rootView: View

    override fun onCreateMultiStateView(
        context: Context,
        inflater: LayoutInflater,
        container: MultiStateContainer
    ): View {
        return inflater.inflate(
            BaseLibrary.config?.pageStatusModelImp?.getErrorRetryStateRes()
                ?: R.layout.mult_state_error_retry, container, false
        )
    }

    override fun onMultiStateViewCreate(view: View) {
        rootView = view
        if (BaseLibrary.config?.pageStatusModelImp != null) {
            BaseLibrary.config?.pageStatusModelImp?.initErrorRetryStateView(view, MultiStatePage.config)
        } else {
            tvErrorMsg = view.findViewById(R.id.tv_error_msg)
            imgError = view.findViewById(R.id.img_error)
            tvRetry = view.findViewById(R.id.retryBtn)
            setErrorMsg(MultiStatePage.config.errorMsg)
            setErrorIcon(MultiStatePage.config.errorIcon)
        }
    }

    override fun enableReload(): Boolean = true

    override fun bindRetryView(): View? {
        return BaseLibrary.config?.pageStatusModelImp?.getErrorRetryView(rootView) ?: tvRetry
    }

    private fun setErrorMsg(errorMsg: String) {
        tvErrorMsg.text = errorMsg
    }

    private fun setErrorIcon(@DrawableRes errorIcon: Int) {
        imgError.setImageResource(errorIcon)
    }
}