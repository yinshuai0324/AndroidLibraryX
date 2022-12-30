package com.ooimi.expand

import android.util.TypedValue
import android.widget.TextView
import androidx.core.widget.TextViewCompat

/**
 * @author 尹帅
 * @Description TextView 扩展方法
 * @createTime 2022年12月30日 11:58
 */


/**
 * 自适应文字大小 默认单位sp
 */
fun TextView.autoSize(min: Int, max: Int, step: Int, unit: Int = TypedValue.COMPLEX_UNIT_SP) {
    TextViewCompat.setAutoSizeTextTypeWithDefaults(this, TextViewCompat.AUTO_SIZE_TEXT_TYPE_UNIFORM)
    TextViewCompat.setAutoSizeTextTypeUniformWithConfiguration(this, min, max, step, unit)
}