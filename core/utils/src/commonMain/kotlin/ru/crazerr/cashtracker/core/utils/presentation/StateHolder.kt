package ru.crazerr.cashtracker.core.utils.presentation

import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value

abstract class StateHolder<State : Any, ViewAction>(
    initialValue: State,
) {
    private var _state: MutableValue<State> = MutableValue(initialValue)
    val state: Value<State> = _state

    fun reduceState(reducer: State.() -> State) {
        _state.value = reducer.invoke(_state.value)
    }

    abstract fun obtainViewAction(action: ViewAction)
}