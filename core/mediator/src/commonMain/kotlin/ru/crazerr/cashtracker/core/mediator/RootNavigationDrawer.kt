package ru.crazerr.cashtracker.core.mediator

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.Icon
import androidx.compose.material3.PermanentNavigationDrawer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import ru.crazerr.cashtracker.core.compose.icons.AppIcons
import ru.crazerr.cashtracker.core.compose.theme.AppTheme

enum class RootNavigationDrawerActions {
    Main,
    Transactions,
    Budget,
    Accounts,
    Goals,
    Settings,
}

data class RootNavigationDrawerItem(
    val icon: Painter,
    val contentDescription: String,
    val action: RootNavigationDrawerActions,
)

private val RootNavigationDrawerMinWidth = 250.dp

@Composable
internal fun RootNavigationDrawer(rootComponent: RootComponent, content: @Composable () -> Unit) {
    val navigationDrawerState = rootComponent.navigationDrawer.subscribeAsState()
    val selectedNavigationDrawerItem = rootComponent.selectedNavigationDrawerItem.subscribeAsState()
    val navigationDrawerItems = listOf(
        RootNavigationDrawerItem(
            icon = AppIcons.Main.painter,
            action = RootNavigationDrawerActions.Main,
            contentDescription = AppIcons.Main.contentDescription,
        ),
        RootNavigationDrawerItem(
            icon = AppIcons.Transactions.painter,
            action = RootNavigationDrawerActions.Transactions,
            contentDescription = AppIcons.Transactions.contentDescription,
        ),
        RootNavigationDrawerItem(
            icon = AppIcons.Budget.painter,
            action = RootNavigationDrawerActions.Budget,
            contentDescription = AppIcons.Budget.contentDescription,
        ),
//        RootNavigationDrawerItem(
//            icon = AppIcons.Accounts.painter,
//            action = RootNavigationDrawerActions.Accounts,
//            contentDescription = AppIcons.Accounts.contentDescription,
//        ),
        RootNavigationDrawerItem(
            icon = AppIcons.Goals.painter,
            action = RootNavigationDrawerActions.Goals,
            contentDescription = AppIcons.Goals.contentDescription,
        ),
        RootNavigationDrawerItem(
            icon = AppIcons.Settings.painter,
            action = RootNavigationDrawerActions.Settings,
            contentDescription = AppIcons.Settings.contentDescription,
        ),
    )

    PermanentNavigationDrawer(
        drawerContent = {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .background(color = AppTheme.Colors.background)
                    .width(RootNavigationDrawerMinWidth)
            ) {
                Spacer(modifier = Modifier.height(AppTheme.Dimens.dimen10))

                navigationDrawerItems.forEach { item ->
                    RootNavigationDrawerItemView(
                        item = item,
                        isSelected = item.action == selectedNavigationDrawerItem.value,
                        onClick = {
                            rootComponent.obtainNavigationDrawerViewAction(item.action)
                        }
                    )
                }
            }
        }
    ) {
        content()
    }


}

@Composable
private fun ColumnScope.RootNavigationDrawerItemView(
    item: RootNavigationDrawerItem,
    isSelected: Boolean,
    onClick: (RootNavigationDrawerActions) -> Unit,
) {
    Box(
        modifier = Modifier
            .clickable { onClick(item.action) }
            .fillMaxWidth()
    ) {
        RootNavigationDrawerItem(
            iconResId = item.icon,
            isSelected = isSelected,
            label = item.contentDescription
        )
    }
}

@Composable
private fun RootNavigationDrawerItem(
    iconResId: Painter,
    label: String,
    isSelected: Boolean,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = AppTheme.Dimens.dimen8, horizontal = AppTheme.Dimens.dimen12),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = iconResId,
            contentDescription = null
        )

        Spacer(modifier = Modifier.width(AppTheme.Dimens.dimen8))

        BasicText(text = label, style = AppTheme.TextStyles.body)
    }
}