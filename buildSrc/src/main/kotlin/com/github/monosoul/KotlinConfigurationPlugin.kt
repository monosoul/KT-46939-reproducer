package com.github.monosoul

import org.gradle.api.JavaVersion.VERSION_11
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.invoke
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.allopen.gradle.AllOpenExtension
import org.jetbrains.kotlin.gradle.plugin.KaptExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

class KotlinConfigurationPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.apply {
            plugin("org.jetbrains.kotlin.jvm")
            plugin("org.jetbrains.kotlin.kapt")
            plugin("org.jetbrains.kotlin.plugin.spring")
            plugin("org.jetbrains.kotlin.plugin.jpa")
            plugin("org.jetbrains.kotlin.plugin.allopen")
        }

        with(project) {
            extensions.configure<JavaPluginExtension> {
                sourceCompatibility = VERSION_11
                @Suppress("UnstableApiUsage")
                withSourcesJar()
            }

            configureKapt()
            configureAllOpen()

            dependencies {
                implementation(enforcedPlatform("org.jetbrains.kotlin:kotlin-bom"))
                implementation(enforcedPlatform("org.jetbrains.kotlinx:kotlinx-coroutines-bom"))

                implementation("org.jetbrains.kotlin:kotlin-reflect")
                implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-slf4j")
                testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
            }

            tasks {
                withType<KotlinCompile>().configureEach {
                    kotlinOptions {
                        jvmTarget = VERSION_11.majorVersion
                        freeCompilerArgs = listOf("-Xjsr305=strict")
                    }
                }
            }
        }
    }

    private fun Project.configureKapt() {
        extensions.configure<KaptExtension> {
            includeCompileClasspath = false
        }
    }

    private fun Project.configureAllOpen() {
        extensions.configure<AllOpenExtension> {
            // some configuration here
        }
    }
}
