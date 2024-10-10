import io.gitlab.arturbosch.detekt.Detekt

plugins {
    alias(libs.plugins.multiplatform).apply(false)
    alias(libs.plugins.compose.compiler).apply(false)
    alias(libs.plugins.compose).apply(false)
    alias(libs.plugins.android.application).apply(false)
    alias(libs.plugins.android.library).apply(false)
    alias(libs.plugins.kotlinx.serialization).apply(false)
    alias(libs.plugins.cocoapods).apply(false)
    alias(libs.plugins.ksp).apply(false)
    alias(libs.plugins.room).apply(false)
    alias(libs.plugins.detekt).apply(true)
    id("base.plugin")
    id("project.plugin")
}

val detektCli: Provider<MinimalExternalModuleDependency> = libs.detekt.cli
val detektFormatting: Provider<MinimalExternalModuleDependency> = libs.detekt.formatting
val detektRulesLibraries: Provider<MinimalExternalModuleDependency> = libs.detekt.rules.libraries
val detektRulesRuleAuthors: Provider<MinimalExternalModuleDependency> = libs.detekt.rules.ruleauthors

allprojects {
    apply(plugin = "io.gitlab.arturbosch.detekt")
    detekt {
        source.setFrom(
            "src/main/java",
            "src/main/kotlin",
            "src/androidMain/kotlin",
            "src/commonMain/kotlin",
            "src/iosMain/kotlin",
            "src/jvmMain/kotlin",
            "src/jsMain/kotlin"
        )
        buildUponDefaultConfig = true
        parallel = true
        ignoreFailures = false
        baseline = file("$rootDir/config/detekt/baseline.xml")
        config.setFrom(
            file("$rootDir/config/detekt/detekt.yml")
        )
    }

    dependencies {
        detekt(detektCli)
        detektPlugins(detektFormatting)
        detektPlugins(detektRulesLibraries)
        detektPlugins(detektRulesRuleAuthors)
    }

    tasks.withType<Detekt>().configureEach {
        jvmTarget = libs.versions.java.get()
        reports {
            html.required.set(true)
            xml.required.set(false)
            txt.required.set(false)
            sarif.required.set(false)
            md.required.set(false)
        }
        basePath = rootDir.absolutePath
        autoCorrect = true
    }

    tasks.withType<io.gitlab.arturbosch.detekt.DetektCreateBaselineTask>().configureEach {
        jvmTarget = libs.versions.java.get()
    }
}