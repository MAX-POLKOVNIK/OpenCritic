plugins {
    alias(libs.plugins.feature)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(projects.feature.authApi)

            implementation(libs.ktor.client.core)
            implementation(libs.moko.resources)
        }
        androidMain.dependencies {
            implementation(libs.ktor.client.okhttp)
            implementation(libs.koin.android)
        }
        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
        }
    }
}

android {
    namespace = "com.opencritic.auth"
}
