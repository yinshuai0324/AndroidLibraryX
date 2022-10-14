package com.ooimi.base.pagestatus

import com.ooimi.base.BaseLibrary
import com.ooimi.base.R

/**
 * @ProjectName: MultiStatePage
 * @CreateDate: 2020/9/19 12:30
 */

class MultiStateConfig() {
    val errorMsg: String
        get() = BaseLibrary.config?.pageStatusModelImp?.getErrorMessage() ?: "哎呀,出错了"
    val networkErrorMsg: String
        get() = BaseLibrary.config?.pageStatusModelImp?.getNetworkErrorMessage() ?: "网络异常"
    val networkErrorIcon: Int
        get() = BaseLibrary.config?.pageStatusModelImp?.getNetworkErrorIcon()
            ?: R.mipmap.state_error
    val errorIcon: Int
        get() = BaseLibrary.config?.pageStatusModelImp?.getErrorIcon() ?: R.mipmap.state_error
    val emptyMsg: String
        get() = BaseLibrary.config?.pageStatusModelImp?.getEmptyMessage() ?: "这里什么都没有"
    val emptyIcon: Int
        get() = BaseLibrary.config?.pageStatusModelImp?.getEmptyIcon() ?: R.mipmap.state_empty
    val loadingMsg: String
        get() = BaseLibrary.config?.pageStatusModelImp?.getLoadingMessage() ?: "正在加载中..."
    var alphaDuration: Long = 500
}