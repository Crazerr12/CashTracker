package ru.crazerr.cashtracker.feature.category.presentation.createCategoryDialog.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cashtracker.feature.category.presentation.generated.resources.Res
import cashtracker.feature.category.presentation.generated.resources.create_category_dialog_name_hint
import cashtracker.feature.category.presentation.generated.resources.create_category_dialog_title
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import org.jetbrains.compose.resources.stringResource
import ru.crazerr.cashtracker.core.compose.components.AppDialog
import ru.crazerr.cashtracker.core.compose.components.AppTextField
import ru.crazerr.cashtracker.core.compose.components.ButtonsFooter
import ru.crazerr.cashtracker.core.compose.theme.AppTheme
import ru.crazerr.cashtracker.feature.category.presentation.api.createCategoryDialog.CreateCategoryComponent
import ru.crazerr.cashtracker.feature.category.presentation.api.createCategoryDialog.CreateCategoryState
import ru.crazerr.cashtracker.feature.category.presentation.api.createCategoryDialog.CreateCategoryViewAction

@Composable
internal fun CreateCategoryView(modifier: Modifier = Modifier, component: CreateCategoryComponent) {
    val state by component.state.subscribeAsState()

    AppDialog(
        modifier = modifier,
        onDismissRequest = { component.obtainViewAction(CreateCategoryViewAction.CancelClick) },
        title = {
            BasicText(
                text = stringResource(Res.string.create_category_dialog_title),
                style = AppTheme.TextStyles.title.copy(color = AppTheme.Colors.black)
            )
        },
        footer = {
            ButtonsFooter(
                modifier = Modifier.align(Alignment.End),
                onCancelClick = { component.obtainViewAction(CreateCategoryViewAction.CancelClick) },
                onSaveClick = { component.obtainViewAction(CreateCategoryViewAction.SaveClick) }
            )
        }
    ) { contentModifier ->
        CreateCategoryViewContent(
            modifier = Modifier.then(contentModifier),
            state = state,
            obtainViewAction = component::obtainViewAction
        )
    }
}

@Composable
private fun CreateCategoryViewContent(
    modifier: Modifier = Modifier,
    state: CreateCategoryState,
    obtainViewAction: (CreateCategoryViewAction) -> Unit,
) {
    Column(modifier = modifier) {
        AppTextField(
            value = state.name,
            onValueChange = { obtainViewAction(CreateCategoryViewAction.UpdateName(it)) },
            hint = stringResource(Res.string.create_category_dialog_name_hint),
        )
    }
}
