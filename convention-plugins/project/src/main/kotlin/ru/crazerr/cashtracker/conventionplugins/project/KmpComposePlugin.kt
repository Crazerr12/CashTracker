package ru.crazerr.cashtracker.conventionplugins.project

import org.gradle.api.Plugin
import org.gradle.api.Project
import ru.crazerr.cashtracker.conventionplugins.base.extensions.libs
import ru.crazerr.cashtracker.conventionplugins.project.extensions.androidMainDependencies
import ru.crazerr.cashtracker.conventionplugins.project.extensions.commonMainDependencies

class KmpComposePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("android.library.plugin")
                apply("kmp.compose.config")
                apply("org.jetbrains.kotlin.plugin.serialization")
            }

            commonMainDependencies {
                implementation(libs.koin.core)
                implementation(libs.koin.compose)
                implementation(libs.compose.ui.tooling.preview)
            }

            androidMainDependencies {
                implementation(libs.androidx.core)
            }
        }
    }
}