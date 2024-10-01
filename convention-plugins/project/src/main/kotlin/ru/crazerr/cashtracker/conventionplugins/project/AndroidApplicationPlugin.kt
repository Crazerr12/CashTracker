package ru.crazerr.cashtracker.conventionplugins.project

import com.android.build.api.dsl.ApplicationDefaultConfig
import org.gradle.api.Plugin
import org.gradle.api.Project
import ru.crazerr.cashtracker.conventionplugins.base.extensions.androidConfig
import ru.crazerr.cashtracker.conventionplugins.base.extensions.libs

class AndroidApplicationPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply(libs.plugins.android.application.get().pluginId)
                apply("android.base.config")
                apply("android.base.test.config")
            }

            androidConfig {
                defaultConfig {
                    this as ApplicationDefaultConfig

                    targetSdk = libs.versions.targetSdk.get().toInt()

                    versionCode = libs.versions.appVersionCode.get().toInt()
                    versionName = libs.versions.appVersionName.get()
                }
            }
        }
    }
}