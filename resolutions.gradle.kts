val kotlinVersion = "1.4.21"
val kotlinCoroutinesVersion = "1.4.2"

gradle.allprojects {
    configurations.configureEach {
        resolutionStrategy {
            force("org.jetbrains.kotlin:kotlin-bom:$kotlinVersion")
            force("org.jetbrains.kotlinx:kotlinx-coroutines-bom:$kotlinCoroutinesVersion")

            eachDependency {
                when (requested.group) {
                    "org.jetbrains.kotlin" -> useVersion(kotlinVersion)
                    "org.jetbrains.kotlinx" -> when(requested.name) {
                        "kotlinx-coroutines-core" -> useVersion(kotlinCoroutinesVersion)
                    }
                }
            }
        }
    }
}
