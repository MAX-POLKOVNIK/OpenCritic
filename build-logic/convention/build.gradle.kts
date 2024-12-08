import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

group = "com.opencritic.build_logic.convention"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

dependencies {
    compileOnly(libs.androidGradlePlugin)
    compileOnly(libs.androidToolsCommon)
    compileOnly(libs.kotlinGradlePlugin)
    compileOnly(libs.kspGradlePlugin)
}

tasks {
    validatePlugins {
        enableStricterValidation = true
        failOnWarning = true
    }
}

gradlePlugin {
    plugins {
        register("kmpFeature") {
            id = "com.opencritic.kmp.feature"
            implementationClass = "KotlinMultiplatformFeaturePlugin"
        }
    }
}
