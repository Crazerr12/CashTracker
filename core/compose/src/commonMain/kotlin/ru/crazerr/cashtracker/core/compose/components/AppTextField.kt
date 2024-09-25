package ru.crazerr.cashtracker.core.compose.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import ru.crazerr.cashtracker.core.compose.theme.AppTheme
import ru.crazerr.cashtracker.core.compose.utils.conditional

@Composable
fun AppTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    enabled: Boolean = true,
    error: String? = "",
    hint: String? = "",
    singleLine: Boolean = true,
    keyboardActions: KeyboardActions = KeyboardActions(),
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
) {
    Column {
        val errorColor = AppTheme.Colors.red
        val textFieldShape = RoundedCornerShape(AppTheme.Dimens.dimen16)

        TextField(
            modifier = modifier
                .appTextFieldModifier()
                .conditional(
                    condition = !error.isNullOrEmpty(),
                    ifTrue = {
                        border(
                            width = 1.dp,
                            color = errorColor,
                            shape = textFieldShape
                        )
                    }
                ),
            value = TextFieldValue(value, TextRange(value.length)),
            onValueChange = { newTextFieldValue ->
                onValueChange.invoke(newTextFieldValue.text)
            },
            label = {
                if (!hint.isNullOrEmpty()) {
                    AppTextFieldHint(text = hint)
                }
            },
            enabled = enabled,
            isError = !error.isNullOrEmpty(),
            shape = textFieldShape,
            colors = appTextFieldColors(),
            singleLine = singleLine,
            keyboardActions = keyboardActions,
            keyboardOptions = keyboardOptions
        )

        if (!error.isNullOrEmpty()) {
            Spacer(modifier = Modifier.height(AppTheme.Dimens.dimen4))

            AppTextFieldSupportingText(error)
        }
    }
}

internal fun Modifier.appTextFieldModifier() = composed {
    this.clip(shape = RoundedCornerShape(AppTheme.Dimens.dimen16)).fillMaxWidth()
}

@Composable
internal fun appTextFieldColors(): TextFieldColors {
    return TextFieldDefaults.colors(
        focusedTextColor = AppTheme.Colors.black,
        errorCursorColor = AppTheme.Colors.black,
        cursorColor = AppTheme.Colors.black,
        errorIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        disabledIndicatorColor = Color.Transparent,
        focusedIndicatorColor = Color.Transparent,
    )
}

@Composable
internal fun AppTextFieldSupportingText(text: String) {
    BasicText(
        text = text,
        style = AppTheme.TextStyles.caption.copy(color = AppTheme.Colors.red),
    )
}

@Composable
internal fun AppTextFieldHint(
    text: String,
    style: TextStyle = AppTheme.TextStyles.input,
) {
    BasicText(
        text = text,
        style = style.copy(color = AppTheme.Colors.yellow),
    )
}