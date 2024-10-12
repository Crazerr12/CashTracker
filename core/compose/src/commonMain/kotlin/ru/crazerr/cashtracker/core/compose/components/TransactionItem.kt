package ru.crazerr.cashtracker.core.compose.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import kotlinx.datetime.LocalDate
import ru.crazerr.cashtracker.core.utils.model.TransactionType

@Composable
expect fun TransactionItem(
    modifier: Modifier = Modifier,
    icon: Painter,
    contentDescription: String? = null,
    title: String,
    category: String,
    date: LocalDate? = null,
    amount: Float,
    type: TransactionType,
    account: String,
    currency: String,
)
