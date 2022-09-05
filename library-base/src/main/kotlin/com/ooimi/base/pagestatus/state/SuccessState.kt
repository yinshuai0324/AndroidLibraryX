package com.ooimi.base.pagestatus.state

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import com.ooimi.base.pagestatus.MultiState
import com.ooimi.base.pagestatus.MultiStateContainer

/**
 * 创建者：yinshuai
 * 创建时间：2021/7/1 13:52
 * 作用描述：成功状态
 */
class SuccessState : MultiState() {
    override fun onCreateMultiStateView(
        context: Context,
        inflater: LayoutInflater,
        container: MultiStateContainer
    ): View {
        return View(context)
    }

    override fun onMultiStateViewCreate(view: View) = Unit

}