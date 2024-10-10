import ru.crazerr.cashtracker.conventionplugins.project.extensions.commonMainDependencies

plugins {
    id("kmp.compose.plugin")
}

commonMainDependencies {
    implementation(project(":core:compose"))
    implementation(project(":core:database"))
    implementation(project(":core:utils"))

    implementation(project(":feature:main:presentation"))
    implementation(project(":feature:budget:presentation"))
    implementation(project(":feature:transactions:presentation"))
    implementation(project(":feature:settings:presentation"))
    implementation(project(":feature:account:presentation"))
    implementation(project(":feature:category:presentation"))
    implementation(project(":feature:transaction:presentation"))

    implementation(libs.coil.compose)
    implementation(libs.coil)

    implementation(libs.decompose)
    implementation(libs.essenty)
    implementation(libs.decompose.extensions)
}

android {
    namespace = "ru.crazerr.cashtracker.core.mediator"
}
