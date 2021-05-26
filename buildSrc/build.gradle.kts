plugins {
    `kotlin-dsl`
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin")
    implementation("org.jetbrains.kotlin:kotlin-allopen")
    implementation("org.jetbrains.kotlin:kotlin-noarg")
}

gradlePlugin {
    plugins {
        register("kotlin-configuration") {
            id = "kotlin-configuration"
            implementationClass = "com.github.monosoul.KotlinConfigurationPlugin"
        }
    }
}
