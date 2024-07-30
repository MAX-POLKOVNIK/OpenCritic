plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.moko.resources)
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }

    val toExport = listOf(
        projects.api,
        projects.logs,
        projects.resources,
        projects.mvvm,
        projects.dashboard,
        projects.games,
        projects.main,
        projects.navigation,
        projects.gameDetails,
        projects.search,
        projects.gameBrowser,
        projects.database,
        projects.gameYour,
        projects.auth,
        libs.moko.resources,
        libs.moko.graphics,
    )

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            isStatic = false
            toExport.forEach { dep ->
                export(dep)
            }
        }
    }

    sourceSets {
        commonMain.dependencies {
            toExport.forEach { api(it) }
            implementation(libs.koin.core)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
        androidMain.dependencies {
            implementation(libs.koin.android)
        }
    }
}

android {
    namespace = "com.opencritic.app"
    compileSdk = 34
    defaultConfig {
        minSdk = 28
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

multiplatformResources {
    resourcesPackage.set("com.opencritic.resources") // required
}

task("testClasses")
