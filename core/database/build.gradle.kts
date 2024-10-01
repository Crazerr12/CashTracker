import ru.crazerr.cashtracker.conventionplugins.project.extensions.commonMainDependencies

plugins {
    id("kmp.base.plugin")
    alias(libs.plugins.ksp)
    alias(libs.plugins.room)
}

room {
    schemaDirectory("$projectDir/schemas")
}

commonMainDependencies {
    implementation(project(":core:utils"))
    implementation(libs.room.runtime)
    implementation(libs.sqlite.bundled)
    implementation(libs.sqlite)
}


android {
    namespace = "ru.crazerr.cashtracker.core.database"
}

dependencies {
    add("kspAndroid", libs.room.compiler)
    add("kspJvm", libs.room.compiler)
//    add("kspIosSimulatorArm64", libs.room.compiler)
//    add("kspIosX64", libs.room.compiler)
//    add("kspIosArm64", libs.room.compiler)
}