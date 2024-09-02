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
        projects.core.api,
        projects.core.logs,
        projects.core.resources,
        projects.core.mvvm,
        projects.feature.dashboard,
        projects.feature.dashboardApi,
        projects.feature.games,
        projects.feature.main,
        projects.core.navigation,
        projects.feature.gameDetails,
        projects.feature.gameDetailsApi,
        projects.feature.search,
        projects.feature.searchApi,
        projects.feature.gameBrowser,
        projects.feature.gameBrowserApi,
        projects.core.database,
        projects.feature.gameListApi,
        projects.feature.gameListImpl,
        projects.feature.authImpl,
        projects.feature.authApi,
        projects.feature.news,
        projects.feature.newsApi,
        projects.feature.calendar,
        projects.feature.calendarApi,
        projects.feature.about,
        projects.feature.aboutApi,
        projects.feature.hallOfFame,
        projects.feature.hallOfFameApi,
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }
}

dependencies {
    implementation(libs.compose.navigation.compose)
}

multiplatformResources {
    resourcesPackage.set("com.opencritic.resources") // required
}

task("testClasses")
