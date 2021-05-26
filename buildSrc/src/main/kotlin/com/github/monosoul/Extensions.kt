package com.github.monosoul

import org.gradle.api.Project
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.api.tasks.SourceSetContainer

fun DependencyHandler.implementation(dependencyNotation: Any) {
    add("implementation", dependencyNotation)
}

fun DependencyHandler.testImplementation(dependencyNotation: Any) {
    add("testImplementation", dependencyNotation)
}

val Project.sourceSets: SourceSetContainer
    get() = properties["sourceSets"] as SourceSetContainer
