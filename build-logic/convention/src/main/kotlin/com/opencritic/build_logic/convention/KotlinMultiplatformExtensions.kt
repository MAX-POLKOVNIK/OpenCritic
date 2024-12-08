package com.opencritic.build_logic.convention

import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

internal fun Project.configureKotlinMultiplatform(
    extension: KotlinMultiplatformExtension
) = extension.apply {
    jvmToolchain(17)

    // targets
    androidTarget()
    iosArm64()
    iosX64()
    iosSimulatorArm64()

    applyDefaultHierarchyTemplate()

    //common dependencies
    sourceSets.apply {
        commonMain {
            dependencies {
                implementation(libs.findLibrary("koin.core").get())
                implementation(libs.findLibrary("kotlinx.coroutines.core").get())
                implementation(libs.findLibrary("kotlinx.datetime").get())
                implementation(libs.findLibrary("moko.resources").get())
                implementation(libs.findLibrary("kotlinx.serialization").get())
                implementation(libs.findLibrary("kotlinx.collections.immutable").get())
            }
        }

        androidMain {
            dependencies {
                implementation(libs.findLibrary("koin.android").get() )
            }
        }

        commonTest.dependencies {
            implementation(kotlin("test"))
        }
    }
}