import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import ru.crazerr.cashtracker.conventionplugins.base.extensions.androidConfig
import ru.crazerr.cashtracker.conventionplugins.base.extensions.kotlinJvmCompilerOptions
import ru.crazerr.cashtracker.conventionplugins.base.extensions.libs
import ru.crazerr.cashtracker.conventionplugins.base.extensions.projectJavaVersion

androidConfig {
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

kotlinJvmCompilerOptions {
    jvmTarget.set(JvmTarget.fromTarget(projectJavaVersion.toString()))
    freeCompilerArgs.add("-Xjdk-release=${projectJavaVersion}")
}