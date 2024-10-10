package ru.crazerr.cashtracker.feature.account.presentation.api.createAccountDialog

import ru.crazerr.cashtracker.core.utils.presentation.StateHolder

abstract class CreateAccountComponent : StateHolder<CreateAccountState, CreateAccountViewAction>(InitialCreateAccountState)