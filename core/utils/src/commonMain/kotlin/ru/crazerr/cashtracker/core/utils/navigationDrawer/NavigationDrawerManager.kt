package ru.crazerr.cashtracker.core.utils.navigationDrawer

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value

class NavigationDrawerManager {
    private var _navigationDrawer: MutableValue<NavigationDrawerState> =
        MutableValue(NavigationDrawerState())
    val navigationDrawer: Value<NavigationDrawerState> = _navigationDrawer

    fun hide() {
        _navigationDrawer.value = _navigationDrawer.value.copy(
            show = false
        )
    }

    fun show() {
        _navigationDrawer.value = _navigationDrawer.value.copy(
            show = true
        )
    }
}

data class NavigationDrawerState(
    val show: Boolean = false,
)

fun ComponentContext.hideNavigationDrawer() {
    navigationDrawerManager().hide()
}

fun ComponentContext.showNavigationDrawer() {
    navigationDrawerManager().show()
}