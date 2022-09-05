package data

/**
 * @枚举作用描述:ViewModel事件类型
 * @作者: 尹帅
 * @创建时间: 2022-09-05 15:55
 */
enum class ViewModelEventType {
    /**
     * 事件类型：显示Toast
     */
    EVENT_TOAST,

    /**
     * 事件类型：显示弹窗
     */
    EVENT_DIALOG,

    /**
     * 事件类型：显示加载弹窗
     */
    EVENT_SHOW_LOADING_DIALOG,

    /**
     * 事件类型：关闭弹窗
     */
    EVENT_DISMISS_LOADING_DIALOG,

    /**
     * 事件类型：关闭当前页面
     */
    EVENT_FINISH_PAGE,

    /**
     * 事件类型：改变页面状态
     */
    EVENT_CHANGE_PAGE_STATUS,

    /**
     * 事件类型：无 不处理该事件类型
     */
    EVENT_NONE
}