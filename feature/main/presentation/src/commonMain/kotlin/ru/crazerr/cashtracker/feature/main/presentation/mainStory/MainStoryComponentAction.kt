package ru.crazerr.cashtracker.feature.main.presentation.mainStory

interface MainStoryComponentAction {
    data object CreateNewAccount : MainStoryComponentAction
    data object CreateNewTransaction : MainStoryComponentAction
    // TODO остальные действия компонента
}
