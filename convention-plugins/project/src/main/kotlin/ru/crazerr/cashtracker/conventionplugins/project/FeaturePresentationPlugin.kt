package ru.crazerr.cashtracker.conventionplugins.project

import org.gradle.api.Plugin
import org.gradle.api.Project
import ru.crazerr.cashtracker.conventionplugins.base.extensions.libs
import ru.crazerr.cashtracker.conventionplugins.project.extensions.androidMainDependencies
import ru.crazerr.cashtracker.conventionplugins.project.extensions.commonMainDependencies

class FeaturePresentationPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("android.library.plugin")
                apply("kmp.compose.config")
                apply("org.jetbrains.kotlin.plugin.serialization")
            }

            commonMainDependencies {
                implementation(project(":core:compose"))
                implementation(project(":core:utils"))

                implementation(libs.koin.core)
                implementation(libs.koin.compose)

                implementation(libs.decompose)
                implementation(libs.decompose.extensions)
                implementation(libs.essenty)

                implementation(libs.kotlinx.coroutines.core)
            }

            androidMainDependencies {
                implementation(libs.kotlinx.coroutines.android)
            }
        }
    }
}