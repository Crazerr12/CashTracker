package ru.crazerr.cashtracker.core.compose.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import kotlinx.datetime.LocalDate
import ru.crazerr.cashtracker.core.compose.theme.AppTheme
import ru.crazerr.cashtracker.core.utils.model.TransactionType

@Suppress("LongParameterList")
@Composable
actual fun TransactionItem(
    modifier: Modifier,
    icon: Painter,
    contentDescription: String?,
    title: String,
    category: String,
    date: LocalDate?,
    amount: Float,
    type: TransactionType,
    account: String,
    currency: String,
) {
    Row(modifier = modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Icon(
            painter = icon,
            contentDescription = contentDescription,

        )

        Spacer(modifier = Modifier.width(AppTheme.Dimens.dimen16))

        Column(modifier = Modifier) {
            BasicText(
                text = title,
                style = AppTheme.TextStyles.body
            )

            Spacer(modifier = Modifier.height(AppTheme.Dimens.dimen4))

            BasicText(
                text = category,
                style = AppTheme.TextStyles.caption
            )
        }

        Spacer(modifier = Modifier.weight(1f).defaultMinSize(minWidth = AppTheme.Dimens.dimen16))

        Column(modifier = Modifier, horizontalAlignment = Alignment.End) {
            BasicText(
                text = if (type == TransactionType.EXPENSE) "-$amount $currency" else "$amount $currency",
                style = AppTheme.TextStyles.body.copy(
                    color = if (type == TransactionType.EXPENSE) {
                        AppTheme.Colors.green
                    } else {
                        AppTheme.Colors.red
                    }
                )
            )

            Spacer(modifier = Modifier.height(AppTheme.Dimens.dimen4))

            Row(modifier = Modifier) {
                if (date != null) {
                    BasicText(
                        text = date.toString(),
                        style = AppTheme.TextStyles.caption
                    )

                    Spacer(modifier = Modifier.width(AppTheme.Dimens.dimen4))
                }

                BasicText(
                    text = "${type.name}, $account",
                    style = AppTheme.TextStyles.caption
                )
            }
        }
    }
}
