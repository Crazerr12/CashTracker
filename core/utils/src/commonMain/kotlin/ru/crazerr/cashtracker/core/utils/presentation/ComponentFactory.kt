package ru.crazerr.cashtracker.core.utils.presentation

import com.arkivanov.decompose.ComponentContext

interface ComponentFactory<Component, ComponentAction> {
    fun create(
        componentContext: ComponentContext,
        onAction: (ComponentAction) -> Unit,
    ): Component
}
