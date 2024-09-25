package ru.crazerr.cashtracker.feature.transactions.presentation.transactionsStory.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.Children
import ru.crazerr.cashtracker.feature.transactions.presentation.transactions.ui.TransactionsView
import ru.crazerr.cashtracker.feature.transactions.presentation.transactionsStory.TransactionsStoryComponent

@Composable
fun TransactionsCoordinator(
    component: TransactionsStoryComponent,
    modifier: Modifier = Modifier,
) {
    Children(
        stack = component.stack,
        modifier = modifier,
    ) {
        when (val child = it.instance) {
            is TransactionsStoryComponent.Child.TransactionsChild -> TransactionsView(component = child.component)
        }
    }
}