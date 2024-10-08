package ru.crazerr.cashtracker.conventionplugins.project

import org.gradle.api.Plugin
import org.gradle.api.Project
import ru.crazerr.cashtracker.conventionplugins.base.extensions.libs
import ru.crazerr.cashtracker.conventionplugins.project.extensions.androidMainDependencies
import ru.crazerr.cashtracker.conventionplugins.project.extensions.commonMainDependencies

class KmpBasePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("android.library.plugin")
                apply("kmp.base.config")
                apply(libs.plugins.kotlinx.serialization.get().pluginId)
            }

            commonMainDependencies {
                implementation(libs.kotlinx.coroutines.core)
                implementation(libs.koin.core)

                implementation(libs.kotlinx.datetime)
            }

            androidMainDependencies {
                implementation(libs.kotlinx.coroutines.android)
            }
        }
    }
}