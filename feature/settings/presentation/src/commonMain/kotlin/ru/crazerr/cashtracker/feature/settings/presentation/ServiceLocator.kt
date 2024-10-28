package ru.crazerr.cashtracker.feature.settings.presentation

import org.koin.dsl.module
import ru.crazerr.cashtracker.feature.settings.data.settingsDataModule
import ru.crazerr.cashtracker.feature.settings.domain.settingsDomainModule

val settingsPresentationModule = module {
    includes(settingsDomainModule, settingsDataModule)
}
