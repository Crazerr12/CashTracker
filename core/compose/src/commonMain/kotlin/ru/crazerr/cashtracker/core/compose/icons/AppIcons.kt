package ru.crazerr.cashtracker.core.compose.icons

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import cashtracker.core.compose.generated.resources.Res
import cashtracker.core.compose.generated.resources.ic_add
import cashtracker.core.compose.generated.resources.ic_arrow_back
import cashtracker.core.compose.generated.resources.ic_arrow_forward
import cashtracker.core.compose.generated.resources.ic_budget
import cashtracker.core.compose.generated.resources.ic_calendar_empty
import cashtracker.core.compose.generated.resources.ic_goals
import cashtracker.core.compose.generated.resources.ic_main
import cashtracker.core.compose.generated.resources.ic_settings
import cashtracker.core.compose.generated.resources.ic_transactions
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

class AppIcon(
    private val drawableResource: DrawableResource,
    private val contentDescriptionResource: StringResource,
) {
    val painter: Painter
        @Composable
        get() = painterResource(resource = drawableResource)

    val contentDescription: String
        @Composable
        get() = stringResource(resource = contentDescriptionResource)
}

object AppIcons {

    val Main: AppIcon
        get() = AppIcon(
            drawableResource = Res.drawable.ic_main,
            contentDescriptionResource = Res.string.ic_main,
        )

    val Transactions: AppIcon
        get() = AppIcon(
            drawableResource = Res.drawable.ic_transactions,
            contentDescriptionResource = Res.string.ic_transactions,
        )

    val Budget: AppIcon
        get() = AppIcon(
            drawableResource = Res.drawable.ic_budget,
            contentDescriptionResource = Res.string.ic_budget,
        )

//    val Accounts: AppIcon
//        get() = AppIcon(
//            drawableResource = Res.drawable.ic_accounts,
//            contentDescriptionResource = Res.string.ic_accounts,
//        )

    val Goals: AppIcon
        get() = AppIcon(
            drawableResource = Res.drawable.ic_goals,
            contentDescriptionResource = Res.string.ic_goals,
        )

    val Settings: AppIcon
        get() = AppIcon(
            drawableResource = Res.drawable.ic_settings,
            contentDescriptionResource = Res.string.ic_settings,
        )

    val PlusIcon: AppIcon
        get() = AppIcon(
            drawableResource = Res.drawable.ic_add,
            contentDescriptionResource = Res.string.ic_add,
        )

    val ArrowForward: AppIcon
        get() = AppIcon(
            drawableResource = Res.drawable.ic_arrow_forward,
            contentDescriptionResource = Res.string.ic_arrow_forward,
        )

    val ArrowBack: AppIcon
        get() = AppIcon(
            drawableResource = Res.drawable.ic_arrow_back,
            contentDescriptionResource = Res.string.ic_arrow_back,
        )

    val AngleDown: AppIcon
        get() = AppIcon(
            drawableResource = Res.drawable.ic_add,
            contentDescriptionResource = Res.string.ic_add
        )

    val AngleUp: AppIcon
        get() = AppIcon(
            drawableResource = Res.drawable.ic_add,
            contentDescriptionResource = Res.string.ic_add
        )

    val Calendar: AppIcon
        get() = AppIcon(
            drawableResource = Res.drawable.ic_calendar_empty,
            contentDescriptionResource = Res.string.ic_calendar_empty
        )
}