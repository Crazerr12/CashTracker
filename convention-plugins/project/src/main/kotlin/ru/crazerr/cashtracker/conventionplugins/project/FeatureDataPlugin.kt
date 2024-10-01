package ru.crazerr.cashtracker.conventionplugins.project

import org.gradle.api.Plugin
import org.gradle.api.Project
import ru.crazerr.cashtracker.conventionplugins.base.extensions.libs
import ru.crazerr.cashtracker.conventionplugins.project.extensions.androidMainDependencies
import ru.crazerr.cashtracker.conventionplugins.project.extensions.commonMainDependencies

class FeatureDataPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("android.library.plugin")
                apply("kmp.base.config")
            }

            commonMainDependencies {
                implementation(project(":core:database"))
                implementation(project(":core:utils"))

                implementation(libs.kotlinx.coroutines.core)
                implementation(libs.koin.core)
                implementation(libs.room.runtime)
            }

            androidMainDependencies {
                implementation(libs.kotlinx.coroutines.android)
            }
        }
    }
}