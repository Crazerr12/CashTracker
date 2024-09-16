package ru.crazerr.cashtracker.core.mediator

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import ru.crazerr.cashtracker.core.compose.icons.AppIcons

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

var RootNavigationDrawerWidth = 56.dp

@Composable
internal fun RootNavigationDrawer(rootComponent: RootComponent) {
    val navigationDrawerState = rootComponent.navigationDrawer.subscribeAsState()
    val selectedNavigationDrawerItem = rootComponent.selectedNavigationDrawerItem.subscribeAsState()
//    val navigationDrawerItems = listOf(
//        RootNavigationDrawerItem(
//            icon = AppIcons.Main.painter,
//            action = RootNavigationDrawerActions.Main,
//            contentDescription = AppIcons.Main.contentDescription,
//        ),
//        RootNavigationDrawerItem(
//            icon = AppIcons.Transactions.painter,
//            action = RootNavigationDrawerActions.Transactions,
//            contentDescription = AppIcons.Transactions.contentDescription,
//        ),
//        RootNavigationDrawerItem(
//            icon = AppIcons.Budget.painter,
//            action = RootNavigationDrawerActions.Budget,
//            contentDescription = AppIcons.Budget.contentDescription,
//        ),
//        RootNavigationDrawerItem(
//            icon = AppIcons.Accounts.painter,
//            action = RootNavigationDrawerActions.Accounts,
//            contentDescription = AppIcons.Accounts.contentDescription,
//        ),
//        RootNavigationDrawerItem(
//            icon = AppIcons.Goals.painter,
//            action = RootNavigationDrawerActions.Goals,
//            contentDescription = AppIcons.Goals.contentDescription,
//        ),
//        RootNavigationDrawerItem(
//            icon = AppIcons.Settings.painter,
//            action = RootNavigationDrawerActions.Settings,
//            contentDescription = AppIcons.Settings.contentDescription,
//        ),
//    )
//
//    if (navigationDrawerState.value.show) {
//        Column(
//            modifier = Modifier.fillMaxHeight().width(RootNavigationDrawerWidth)
//        ) {
//            navigationDrawerItems.forEach { item ->
//                RootNavigationDrawerItemView(
//                    item = item,
//                    isSelected = item.action == selectedNavigationDrawerItem.value,
//                    onClick = {
//                        rootComponent.obtainNavigationDrawerViewAction(item.action)
//                    }
//                )
//            }
//        }
//    }
}

@Composable
private fun ColumnScope.RootNavigationDrawerItemView(
    item: RootNavigationDrawerItem,
    isSelected: Boolean,
    onClick: (RootNavigationDrawerActions) -> Unit,
) {
    Box(
        modifier = Modifier
            .padding()
            .clickable { onClick(item.action) }
            .fillMaxWidth()
            .weight(1f, true)
    ) {
        RootNavigationDrawerItem(
            iconResId = item.icon,
            isSelected = isSelected,
        )
    }
}

@Composable
private fun RootNavigationDrawerItem(
    iconResId: Painter,
    isSelected: Boolean,
    modifier: Modifier = Modifier,
) {
    val tintColor = if (isSelected) "один" else "второй"
    Box(
        modifier = Modifier.then(modifier),
    ) {
    }
}