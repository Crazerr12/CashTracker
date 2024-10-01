package ru.crazerr.cashtracker.conventionplugins.project.extensions

import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.plugin.mpp.Framework
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget
import ru.crazerr.cashtracker.conventionplugins.base.extensions.kotlinMultiplatformConfig

fun Project.iosRegularFramework(
    block: Framework.() -> Unit,
) {
    kotlinMultiplatformConfig {
        targets
            .filterIsInstance<KotlinNativeTarget>()
            .forEach { nativeTarget -> nativeTarget.binaries.framework(configure = block) }
    }
}