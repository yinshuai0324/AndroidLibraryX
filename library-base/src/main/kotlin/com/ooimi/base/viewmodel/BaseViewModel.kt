package com.ooimi.base.viewmodel

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blankj.utilcode.util.ActivityUtils
import com.ooimi.base.BaseLibrary
import com.ooimi.base.pagestatus.PageStatus
import com.ooimi.base.data.ViewModelEventData
import com.ooimi.base.data.ViewModelEventType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.ooimi.base.livedata.SingleLiveEvent

/**
 * @类作用描述:ViewModel基类
 * @作者: 尹帅
 * @创建时间: 2022-09-05 15:51
 */
open class BaseViewModel : ViewModel() {
    /**
     * 事件通知
     */
    val eventNoticeData = SingleLiveEvent<ViewModelEventData>()

    /**
     * 每页的数据条数
     */
    val pageSize = BaseLibrary.config?.defaultPageSize ?: 20

    /**
     * toast
     */
    fun toast(msg: String?) {
        msg?.let {
            viewModelScope.launch(Dispatchers.Main) {
                eventNoticeData.value =
                    ViewModelEventData(type = ViewModelEventType.EVENT_TOAST, desc = it)
            }
        }
    }

    /**
     * 显示弹窗
     */
    fun showDialog(title: String = "", msg: String = "") {
        viewModelScope.launch(Dispatchers.Main) {
            eventNoticeData.value =
                ViewModelEventData(ViewModelEventType.EVENT_DIALOG, title = title, desc = msg)
        }
    }


    /**
     * 显示加载框
     */
    fun showLoading(msg: String = "正在加载中...") {
        viewModelScope.launch(Dispatchers.Main) {
            eventNoticeData.value =
                ViewModelEventData(ViewModelEventType.EVENT_SHOW_LOADING_DIALOG, desc = msg)
        }
    }

    /**
     * 关闭加载框
     */
    fun dismissLoading() {
        viewModelScope.launch(Dispatchers.Main) {
            eventNoticeData.value =
                ViewModelEventData(ViewModelEventType.EVENT_DISMISS_LOADING_DIALOG)
        }
    }

    /**
     * 关闭当前页面
     */
    fun finishPage() {
        viewModelScope.launch(Dispatchers.Main) {
            eventNoticeData.value = ViewModelEventData(ViewModelEventType.EVENT_FINISH_PAGE)
        }
    }

    /**
     * 刷新页面数据
     */
    fun refreshPageData() {
        viewModelScope.launch(Dispatchers.Main) {
            eventNoticeData.value = ViewModelEventData(ViewModelEventType.EVENT_REFRESH_DATA)
        }
    }


    /**
     * 改变页面状态
     */
    fun changePageStatus(status: PageStatus) {
        viewModelScope.launch(Dispatchers.Main) {
            eventNoticeData.value =
                ViewModelEventData(ViewModelEventType.EVENT_CHANGE_PAGE_STATUS, pageStatus = status)
        }
    }

    /**
     * 重置刷新状态
     */
    fun resetRefreshStatus() {
        viewModelScope.launch(Dispatchers.Main) {
            eventNoticeData.value =
                ViewModelEventData(ViewModelEventType.EVENT_RESET_REFRESH_STATUS)
        }
    }

    /**
     * 获取Activity
     */
    fun getActivity(): Activity {
        return ActivityUtils.getTopActivity()
    }
}