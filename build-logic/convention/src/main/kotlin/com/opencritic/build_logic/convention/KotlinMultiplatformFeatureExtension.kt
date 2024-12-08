package com.opencritic.build_logic.convention

import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

internal fun Project.configureKotlinMultiplatformFeature(
    extension: KotlinMultiplatformExtension
) = extension.apply {
    apply(::configureKotlinMultiplatform)

    //common dependencies
    sourceSets.apply {
        commonMain {
            dependencies {
                implementation(project(":core:api"))
                implementation(project(":core:database"))
                implementation(project(":core:logs"))
                implementation(project(":core:mvvm"))
                implementation(project(":core:navigation"))
                implementation(project(":core:remote-images"))
                implementation(project(":core:resources"))
            }
        }
        androidMain {
            dependencies {
                implementation(libs.findLibrary("koin.compose").get())
                implementation(libs.findLibrary("coil").get())

                implementation(libs.findLibrary("androidx.lifecycle.runtime.ktx").get())
                implementation(libs.findLibrary("androidx.lifecycle.viewmodel.ktx").get())
                implementation(libs.findLibrary("compose.ui").get())
                implementation(libs.findLibrary("compose.ui.tooling.preview").get())
                implementation(libs.findLibrary("compose.material3").get())
                implementation(libs.findLibrary("androidx.activity.compose").get())
                implementation(libs.findLibrary("compose.navigation.compose").get())
                implementation(libs.findLibrary("androidx.adaptive.android").get())
            }
        }
    }
}