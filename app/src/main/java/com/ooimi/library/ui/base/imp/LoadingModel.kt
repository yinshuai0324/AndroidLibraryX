package com.ooimi.library.ui.base.imp

import android.view.View
import android.widget.TextView
import com.ooimi.base.imp.BaseLoadingModel
import com.ooimi.library.R

/**
 * @类作用描述:
 * @作者: 尹帅
 * @创建时间: 2022-10-14 10:18
 */
class LoadingModel : BaseLoadingModel {
    override fun getLayoutResId(): Int {
        return R.layout.view_loading_widget
    }

    override fun initialize(rootView: View, msg: String) {
        rootView.findViewById<TextView>(R.id.loadingMsg).text = msg
    }

    override fun isCancelableDismiss(): Boolean {
        return true
    }

    override fun isTouchOutsideDismiss(): Boolean {
        return true
    }
}