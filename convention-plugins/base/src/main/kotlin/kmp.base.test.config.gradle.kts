import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSetTree
import ru.crazerr.cashtracker.conventionplugins.base.extensions.debugImplementation
import ru.crazerr.cashtracker.conventionplugins.base.extensions.implementation
import ru.crazerr.cashtracker.conventionplugins.base.extensions.kotlinAndroidTarget
import ru.crazerr.cashtracker.conventionplugins.base.extensions.libs

kotlinAndroidTarget {
    @OptIn(ExperimentalKotlinGradlePluginApi::class)
    instrumentedTestVariant.sourceSetTree.set(KotlinSourceSetTree.test)

    dependencies {
        debugImplementation(libs.androidx.uitest.testManifest)
        implementation(libs.androidx.uitest.junit4)
    }
}