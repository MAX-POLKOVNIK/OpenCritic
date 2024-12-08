plugins {
    alias(libs.plugins.feature)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(projects.feature.searchApi)

            implementation(projects.feature.gameDetailsApi)
        }
    }
}

android {
    namespace = "com.opencritic.search"
}