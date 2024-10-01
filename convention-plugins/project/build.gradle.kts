import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

group = "ru.crazerr.cashtracker.conventionplugins"

dependencies {
    implementation(libs.gradleplugin.android)
    implementation(libs.gradleplugin.kotlin)
    implementation(libs.gradleplugin.compose)
    implementation(libs.gradleplugin.composeCompiler)

    // Workaround for version catalog working inside precompiled scripts
    // Issue - https://github.com/gradle/gradle/issues/15383
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
    implementation(libs.gradleplugin.base)
}

private val projectJavaVersion: JavaVersion = JavaVersion.toVersion(libs.versions.java.get())

java {
    sourceCompatibility = projectJavaVersion
    targetCompatibility = projectJavaVersion
}

tasks.withType<KotlinCompile>().configureEach {
    compilerOptions.jvmTarget.set(JvmTarget.fromTarget(projectJavaVersion.toString()))
}

gradlePlugin {
    plugins {
        register("android.application.plugin") {
            id = "android.application.plugin"
            implementationClass = "ru.crazerr.cashtracker.conventionplugins.project.AndroidApplicationPlugin"
        }

        register("android.library.plugin") {
            id = "android.library.plugin"
            implementationClass = "ru.crazerr.cashtracker.conventionplugins.project.AndroidLibraryPlugin"
        }

        register("kmp.base.plugin") {
            id = "kmp.base.plugin"
            implementationClass = "ru.crazerr.cashtracker.conventionplugins.project.KmpBasePlugin"
        }

        register("kmp.compose.plugin") {
            id = "kmp.compose.plugin"
            implementationClass = "ru.crazerr.cashtracker.conventionplugins.project.KmpComposePlugin"
        }

        register("kmp.compose.application.plugin") {
            id = "kmp.compose.application.plugin"
            implementationClass = "ru.crazerr.cashtracker.conventionplugins.project.KmpComposeApplicationPlugin"
        }

        register("feature.data.plugin") {
            id = "feature.data.plugin"
            implementationClass = "ru.crazerr.cashtracker.conventionplugins.project.FeatureDataPlugin"
        }

        register("feature.domain.plugin") {
            id = "feature.domain.plugin"
            implementationClass = "ru.crazerr.cashtracker.conventionplugins.project.FeatureDomainPlugin"
        }

        register("feature.presentation.plugin") {
            id = "feature.presentation.plugin"
            implementationClass = "ru.crazerr.cashtracker.conventionplugins.project.FeaturePresentationPlugin"
        }
    }
}