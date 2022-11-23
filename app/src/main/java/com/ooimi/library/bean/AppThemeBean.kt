package com.ooimi.library.bean

import androidx.annotation.Keep

@Keep
class AppThemeBean {
    /**
     * 开屏页
     */
    var openPic: String? = ""

    /**
     * tab1未选中icon
     */
    var t1Unselect: String? = ""

    /**
     * tab1选中icon
     */
    var t1Select: String? = ""

    /**
     * tab1文字
     */
    var t1Text: String? = ""

    /**
     * tab2未选中icon
     */
    var t2Unselect: String? = ""

    /**
     * tab2选中icon
     */
    var t2Select: String? = ""

    /**
     * tab2文字
     */
    var t2Text: String? = ""


    /**
     * tab3未选中icon
     */
    var t3Unselect: String? = ""

    /**
     * tab3选中icon
     */
    var t3Select: String? = ""

    /**
     * tab3文字
     */
    var t3Text: String? = ""


    /**
     * tab4未选中icon
     */
    var t4Unselect: String? = ""

    /**
     * tab4选中icon
     */
    var t4Select: String? = ""

    /**
     * tab4文字
     */
    var t4Text: String? = ""


    /**
     * tab5未选中icon
     */
    var t5Unselect: String? = ""

    /**
     * tab5选中icon
     */
    var t5Select: String? = ""

    /**
     * tab4文字
     */
    var t5Text: String? = ""

    /**
     * tab选中的颜色
     */
    var selectColor: String? = ""

    /**
     * tab未选中的颜色
     */
    var unselectColor: String? = ""

    /**
     * 租号默认选中的tab
     */
    var rentGameId: Long = 0

    /**
     * 卖号默认选中的tab
     */
    var dealGameId: Long = 0

    /**
     * 开屏等待时间
     */
    var openTimeMs: Long = 1000

    /**
     * 首页tab需要显示的tab
     */
     var homeTab: List<String>? = arrayListOf()

    /**
     * 首页默认选中
     */
     var homeTabDefaultIndex = 0

}