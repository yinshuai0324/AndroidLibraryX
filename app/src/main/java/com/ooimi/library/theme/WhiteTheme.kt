package com.ooimi.library.theme

import android.content.Context
import androidx.core.content.ContextCompat
import com.ooimi.library.R

class WhiteTheme : BaseAppTheme {
    override fun firstActivityBackgroundColor(context: Context): Int {
        return ContextCompat.getColor(context, R.color.white)
    }

    override fun firstActivityTextColor(context: Context): Int {
        return ContextCompat.getColor(context, R.color.black)
    }

    override fun firstActivityIconColor(context: Context): Int {
        return ContextCompat.getColor(context, R.color.white)
    }

    override fun id(): Int = 0
}