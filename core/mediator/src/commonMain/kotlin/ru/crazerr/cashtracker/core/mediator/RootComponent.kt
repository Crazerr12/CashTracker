package ru.crazerr.cashtracker.core.mediator

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.backhandler.BackHandlerOwner
import kotlinx.serialization.Serializable
import ru.crazerr.cashtracker.feature.main.presentation.mainStory.MainStoryComponent
import ru.crazerr.cashtracker.core.utils.navigationDrawer.NavigationDrawerState
import ru.crazerr.cashtracker.core.utils.navigationDrawer.navigationDrawerManager
import ru.crazerr.cashtracker.core.utils.toast.ToastState
import ru.crazerr.cashtracker.core.utils.toast.toastManager

interface RootComponent : BackHandlerOwner {

    val stack: Value<ChildStack<*, Child>>

    val toast: Value<ToastState>

    val navigationDrawer: Value<NavigationDrawerState>

    val selectedNavigationDrawerItem: Value<RootNavigationDrawerActions>

    fun obtainNavigationDrawerViewAction(actions: RootNavigationDrawerActions)

    sealed interface Child {
        class MainChild(val component: MainStoryComponent) : Child
        class TransactionChild(val component: MainStoryComponent) : Child
        class SettingsChild(val component: MainStoryComponent) : Child
        class BudgetChild(val component: MainStoryComponent) : Child
    }

    @Serializable
    sealed interface Config {
        data object MainStory : Config
        data object TransactionStory : Config
        data object SettingsStory : Config
        data object BudgetStory : Config
    }

    interface Factory {
        fun create(componentContext: ComponentContext): RootComponent
    }
}

internal class RootComponentImpl(
    private val componentContext: ComponentContext,
) : RootComponent, ComponentContext by componentContext {

    private val navigation = StackNavigation<RootComponent.Config>()

    private val di: InternalComponent by lazy {
        InternalComponent.create()
    }

    private val toastManager = toastManager()

    private val navigationDrawerManager = navigationDrawerManager()

    override val toast: Value<ToastState> get() = toastManager.toast

    override val navigationDrawer: Value<NavigationDrawerState>
        get() = navigationDrawerManager.navigationDrawer

    private var _selectedNavigationDrawerItem: MutableValue<RootNavigationDrawerActions> =
        MutableValue(RootNavigationDrawerActions.Main)
    override val selectedNavigationDrawerItem: Value<RootNavigationDrawerActions> get() = _selectedNavigationDrawerItem

    override val stack: Value<ChildStack<*, RootComponent.Child>> = childStack(
        source = navigation,
        serializer = RootComponent.Config.serializer(),
        initialConfiguration = RootComponent.Config.MainStory,
        handleBackButton = true,
        childFactory = ::child
    )

    override fun obtainNavigationDrawerViewAction(actions: RootNavigationDrawerActions) {
        when (actions) {
            RootNavigationDrawerActions.Main -> {
                _selectedNavigationDrawerItem.value = actions
                navigation.bringToFront(RootComponent.Config.MainStory)
            }

            RootNavigationDrawerActions.Budget -> {
                _selectedNavigationDrawerItem.value = actions
                navigation.bringToFront(RootComponent.Config.BudgetStory)
            }

            RootNavigationDrawerActions.Settings -> {
                _selectedNavigationDrawerItem.value = actions
                navigation.bringToFront(RootComponent.Config.SettingsStory)
            }

            RootNavigationDrawerActions.Transactions -> {
                _selectedNavigationDrawerItem.value = actions
                navigation.bringToFront(RootComponent.Config.TransactionStory)
            }

            RootNavigationDrawerActions.Accounts -> {
                _selectedNavigationDrawerItem.value = actions
                navigation.bringToFront(RootComponent.Config.TransactionStory)
            }
            RootNavigationDrawerActions.Goals -> {
                _selectedNavigationDrawerItem.value = actions
                navigation.bringToFront(RootComponent.Config.TransactionStory)
            }
        }
    }

    private fun child(
        config: RootComponent.Config,
        componentContext: ComponentContext,
    ): RootComponent.Child =
        when (config) {
            RootComponent.Config.BudgetStory -> TODO()
            RootComponent.Config.MainStory -> RootComponent.Child.MainChild(
                component = di.mainStoryComponentFactory.create(
                    componentContext = componentContext,
                    onAction = { action ->
                        when (action) {

                        }
                    }
                )
            )

            RootComponent.Config.SettingsStory -> TODO()
            RootComponent.Config.TransactionStory -> TODO()
        }

    class FactoryImpl : RootComponent.Factory {
        override fun create(componentContext: ComponentContext): RootComponent {
            return RootComponentImpl(
                componentContext = componentContext
            )
        }
    }
}