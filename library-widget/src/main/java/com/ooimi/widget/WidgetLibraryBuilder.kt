package com.ooimi.widget

import com.ooimi.widget.imp.BaseLoadImageModel

/**
 * @类作用描述:Widget框架参数配置
 * @作者: 尹帅
 * @创建时间: 2022-10-13 19:33
 */
class WidgetLibraryBuilder {
    /**
     * 是否启用日志
     */
    internal var loadImageModel: BaseLoadImageModel? = null


    fun init() = WidgetLibrary.init(this)
}