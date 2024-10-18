package ru.crazerr.cashtracker.core.compose.icons

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import cashtracker.core.compose.generated.resources.Res
import cashtracker.core.compose.generated.resources.ic_add
import cashtracker.core.compose.generated.resources.ic_angle_down
import cashtracker.core.compose.generated.resources.ic_angle_up
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
    private val name: String,
    private val drawableResource: DrawableResource,
    private val contentDescriptionResource: StringResource,
) {
    val painter: Painter
        @Composable
        get() = painterResource(resource = drawableResource)

    val contentDescription: String
        @Composable
        get() = stringResource(resource = contentDescriptionResource)

    val id: String
        get() = name
}

object AppIcons {
    val Main: AppIcon
        get() = AppIcon(
            name = "ic_main",
            drawableResource = Res.drawable.ic_main,
            contentDescriptionResource = Res.string.ic_main,
        )

    val Transactions: AppIcon
        get() = AppIcon(
            name = "ic_transactions",
            drawableResource = Res.drawable.ic_transactions,
            contentDescriptionResource = Res.string.ic_transactions,
        )

    val Budget: AppIcon
        get() = AppIcon(
            name = "ic_budget",
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
            name = "ic_goals",
            drawableResource = Res.drawable.ic_goals,
            contentDescriptionResource = Res.string.ic_goals,
        )

    val Settings: AppIcon
        get() = AppIcon(
            name = "ic_settings",
            drawableResource = Res.drawable.ic_settings,
            contentDescriptionResource = Res.string.ic_settings,
        )

    val PlusIcon: AppIcon
        get() = AppIcon(
            name = "ic_add",
            drawableResource = Res.drawable.ic_add,
            contentDescriptionResource = Res.string.ic_add,
        )

    val ArrowForward: AppIcon
        get() = AppIcon(
            name = "ic_arrow_forward",
            drawableResource = Res.drawable.ic_arrow_forward,
            contentDescriptionResource = Res.string.ic_arrow_forward,
        )

    val ArrowBack: AppIcon
        get() = AppIcon(
            name = "ic_arrow_back",
            drawableResource = Res.drawable.ic_arrow_back,
            contentDescriptionResource = Res.string.ic_arrow_back,
        )

    val AngleDown: AppIcon
        get() = AppIcon(
            name = "ic_angle_down",
            drawableResource = Res.drawable.ic_angle_down,
            contentDescriptionResource = Res.string.ic_angle_down
        )

    val AngleUp: AppIcon
        get() = AppIcon(
            name = "ic_angle_up",
            drawableResource = Res.drawable.ic_angle_up,
            contentDescriptionResource = Res.string.ic_angle_up
        )

    val Calendar: AppIcon
        get() = AppIcon(
            name = "ic_calendar_empty",
            drawableResource = Res.drawable.ic_calendar_empty,
            contentDescriptionResource = Res.string.ic_calendar_empty
        )

    val categoryIcons = listOf(
        Calendar
    )
}

fun getIconById(id: String): AppIcon =
    when (id) {
        AppIcons.Main.id -> AppIcons.Main
        AppIcons.Transactions.id -> AppIcons.Transactions
        AppIcons.Budget.id -> AppIcons.Budget
        AppIcons.Settings.id -> AppIcons.Settings
        AppIcons.Goals.id -> AppIcons.Goals
        AppIcons.PlusIcon.id -> AppIcons.PlusIcon
        AppIcons.AngleUp.id -> AppIcons.AngleUp
        AppIcons.Calendar.id -> AppIcons.Calendar
        AppIcons.AngleDown.id -> AppIcons.AngleDown
        AppIcons.ArrowBack.id -> AppIcons.ArrowBack
        AppIcons.ArrowForward.id -> AppIcons.ArrowForward
        else -> AppIcons.Transactions
    }
