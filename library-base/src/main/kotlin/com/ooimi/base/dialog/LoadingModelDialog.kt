package com.ooimi.base.dialog

import android.app.Dialog
import android.content.Context
import android.text.TextUtils
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.widget.LinearLayout
import android.widget.TextView
import com.ooimi.base.BaseLibrary
import com.ooimi.base.R

/**
 * @类作用描述:基础的加载框
 * @作者: 尹帅
 * @创建时间: 2022-10-13 17:58
 */
class LoadingModelDialog {
    private val mContext: Context
    private lateinit var dialog: Dialog
    private var message: String = "正在加载中..."
    private var loadingModelImp = BaseLibrary.config?.loadingModelImp
    private var rootView: View? = null

    constructor(context: Context) {
        mContext = context
        createLoadingView()
    }

    private fun createLoadingView() {
        rootView = if (loadingModelImp != null) {
            View.inflate(mContext, loadingModelImp?.getLayoutResId() ?: 0, null)
        } else {
            View.inflate(mContext, R.layout.view_default_loading_widget, null)
        }
        if (loadingModelImp != null) {
            loadingModelImp?.updateUIData(rootView, message)
        } else {
            rootView?.findViewById<TextView>(R.id.loadingMsg)?.text = message
        }
        dialog = Dialog(mContext, R.style.loading)
        // 是否可以按“返回键”消失
        dialog.setCancelable(loadingModelImp?.isCancelableDismiss() ?: false)
        // 点击加载框以外的区域
        dialog.setCanceledOnTouchOutside(loadingModelImp?.isTouchOutsideDismiss() ?: false)
        // 设置布局
        dialog.setContentView(
            rootView, LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
            )
        )
    }

    fun setMessage(msg: String?) {
        if (!TextUtils.isEmpty(msg)) {
            this.message = msg ?: ""
        } else {
            this.message = "正在加载中..."
        }
        if (loadingModelImp != null) {
            loadingModelImp?.updateUIData(rootView, message)
        } else {
            rootView?.findViewById<TextView>(R.id.loadingMsg)?.text = message
        }
    }

    fun show(): Dialog {
        val window = dialog.window
        val lp = window?.attributes
        lp?.width = WindowManager.LayoutParams.WRAP_CONTENT
        lp?.height = WindowManager.LayoutParams.WRAP_CONTENT
        window?.setGravity(Gravity.CENTER)
        window?.attributes = lp
        dialog.show()
        return dialog
    }


    fun dismiss() {
        if (dialog.isShowing) {
            dialog.dismiss()
        }
    }

    fun isShow(): Boolean {
        return dialog.isShowing
    }

    fun getDialog(): Dialog {
        return dialog
    }

}