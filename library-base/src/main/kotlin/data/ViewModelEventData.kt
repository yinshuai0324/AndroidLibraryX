package data

import pagestatus.PageStatus

/**
 * @类作用描述: ViewModel事件类型
 * @作者: 尹帅
 * @创建时间: 2022-09-05 15:54
 */
data class ViewModelEventData(
    val type: ViewModelEventType = ViewModelEventType.EVENT_NONE,
    val pageStatus: PageStatus = PageStatus.STATUS_SUCCEED,
    val title: String = "",
    val desc: String = ""
)