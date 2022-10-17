package com.ooimi.library.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.duodian.common.expand.dp
import com.ooimi.library.R
import com.ooimi.widget.button.AppButton

/**
 * @author 尹帅
 * @date 2022/7/26 15:41
 * @desc
 */
class HomeAdapter : BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_home) {
    override fun convert(holder: BaseViewHolder, item: String) {
        val appButton = holder.getView<AppButton>(R.id.name)
        holder.setText(R.id.name, item)
        appButton.setRadius(10f.dp)
        appButton.setBackgroundColors(R.color.color_45B97C)
    }
}