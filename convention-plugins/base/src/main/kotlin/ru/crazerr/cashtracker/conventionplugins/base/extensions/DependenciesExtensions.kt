package ru.crazerr.cashtracker.conventionplugins.base.extensions

import org.gradle.api.provider.Provider
import org.gradle.kotlin.dsl.DependencyHandlerScope

fun DependencyHandlerScope.implementation(dependency: Provider<out Any?>) {
    add("implementation", dependency)
}

fun DependencyHandlerScope.debugImplementation(dependency: Provider<out Any?>) {
    add("debugImplementation", dependency)
}