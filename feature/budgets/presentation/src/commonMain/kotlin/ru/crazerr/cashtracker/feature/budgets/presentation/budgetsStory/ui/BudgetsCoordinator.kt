package ru.crazerr.cashtracker.feature.budgets.presentation.budgetsStory.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.Children
import ru.crazerr.cashtracker.feature.budgets.presentation.budgets.ui.BudgetsView
import ru.crazerr.cashtracker.feature.budgets.presentation.budgetsStory.BudgetsStoryComponent

@Composable
fun BudgetsCoordinator(
    modifier: Modifier = Modifier,
    component: BudgetsStoryComponent,
) {
    Children(
        stack = component.stack,
        modifier = modifier,
    ) {
        when (val child = it.instance) {
            is BudgetsStoryComponent.Child.Budgets -> BudgetsView(component = child.component)
        }
    }
}
