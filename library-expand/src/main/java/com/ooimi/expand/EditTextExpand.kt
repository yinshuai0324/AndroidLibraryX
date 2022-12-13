package com.ooimi.expand

import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.widget.EditText

/**
 * 输入框扩展方法
 */

/**
 * 移动光标到最后
 */
fun EditText.moveCursorToLast() {
    setSelection(if (TextUtils.isEmpty(this.text)) 0 else this.text.length)
}


/**
 * 监听文字输入
 */
fun EditText.addTextChangeCallback(callback: (text: String?) -> Unit) {
    addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

        }

        override fun afterTextChanged(s: Editable?) {
            callback.invoke(s?.toString() ?: "")
        }
    })
}