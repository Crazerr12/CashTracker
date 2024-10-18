package ru.crazerr.cashtracker.feature.category.presentation.createCategoryDialog.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.DropdownMenu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cashtracker.feature.category.presentation.generated.resources.Res
import cashtracker.feature.category.presentation.generated.resources.create_category_dialog_name_hint
import cashtracker.feature.category.presentation.generated.resources.create_category_dialog_title
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.github.skydoves.colorpicker.compose.HsvColorPicker
import com.github.skydoves.colorpicker.compose.rememberColorPickerController
import org.jetbrains.compose.resources.stringResource
import ru.crazerr.cashtracker.core.compose.components.AppDialog
import ru.crazerr.cashtracker.core.compose.components.AppIconButton
import ru.crazerr.cashtracker.core.compose.components.AppTextField
import ru.crazerr.cashtracker.core.compose.components.ButtonsFooter
import ru.crazerr.cashtracker.core.compose.icons.AppIcons
import ru.crazerr.cashtracker.core.compose.theme.AppTheme
import ru.crazerr.cashtracker.feature.category.presentation.api.createCategoryDialog.CreateCategoryComponent
import ru.crazerr.cashtracker.feature.category.presentation.api.createCategoryDialog.CreateCategoryState
import ru.crazerr.cashtracker.feature.category.presentation.api.createCategoryDialog.CreateCategoryViewAction

private const val ICONS_GRID_ROWS = 5

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
    val controller = rememberColorPickerController()
    Column(modifier = modifier) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            AppIconButton(
                backgroundTint = state.selectedColor,
                icon = state.selectedIcon.painter,
                contentDescription = state.selectedIcon.contentDescription,
                onClick = { obtainViewAction(CreateCategoryViewAction.ManageIconsDropdownMenu) }
            )

            DropdownMenu(
                expanded = state.iconsDropdownIsExpanded,
                onDismissRequest = { obtainViewAction(CreateCategoryViewAction.ManageIconsDropdownMenu) },
            ) {
                LazyHorizontalGrid(
                    rows = GridCells.Fixed(ICONS_GRID_ROWS),
                    modifier = Modifier.size(width = 300.dp, height = 180.dp),
                    contentPadding = PaddingValues(all = AppTheme.Dimens.dimen4)
                ) {
                    items(AppIcons.categoryIcons) {
                        AppIconButton(
                            icon = it.painter,
                            contentDescription = it.contentDescription,
                            onClick = { obtainViewAction(CreateCategoryViewAction.UpdateIcon(it)) }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.width(AppTheme.Dimens.dimen8))

            AppTextField(
                modifier = Modifier,
                value = state.name,
                onValueChange = { obtainViewAction(CreateCategoryViewAction.UpdateName(it)) },
                hint = stringResource(Res.string.create_category_dialog_name_hint),
            )
        }

        Spacer(modifier = Modifier.height(AppTheme.Dimens.dimen8))

        HsvColorPicker(
            modifier = Modifier.fillMaxWidth()
                .height(AppTheme.Dimens.dimen200)
                .padding(AppTheme.Dimens.dimen10),
            controller = controller,
            onColorChanged = { colorEnvelope ->
                obtainViewAction(CreateCategoryViewAction.UpdateColor(colorEnvelope.color))
            }
        )
    }
}
