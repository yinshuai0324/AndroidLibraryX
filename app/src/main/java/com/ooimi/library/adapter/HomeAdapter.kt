package com.ooimi.library.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.ooimi.library.R

/**
 * @author 尹帅
 * @date 2022/7/26 15:41
 * @desc
 */
class HomeAdapter : BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_home) {
    override fun convert(holder: BaseViewHolder, item: String) {
        holder.setText(R.id.name,item)
    }
}