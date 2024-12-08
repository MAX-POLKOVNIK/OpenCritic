plugins {
    alias(libs.plugins.feature)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.core.resources)
            implementation(projects.core.remoteImages)

            implementation(libs.moko.resources)
        }
    }
}

android {
    namespace = "com.opencritic.games"
}