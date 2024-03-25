package com.common.ui.utils

import androidx.annotation.PluralsRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource

sealed class UIText {

    data class DynamicText(val value: String) : UIText()

    class StringResource(@StringRes val resId: Int, vararg val args: Any) : UIText()

    class PluralsResource(@PluralsRes val resId: Int, val quantity: Int, vararg val args: Any) :
        UIText()

    @Composable
    fun asString(): String {
        return when (this) {
            is DynamicText -> value
            is StringResource -> stringResource(resId, *args)
            is PluralsResource -> pluralStringResource(resId, quantity, *args)
        }
    }
}