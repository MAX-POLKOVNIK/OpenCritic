plugins {
    alias(libs.plugins.feature)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(projects.feature.aboutApi)
        }
    }
}

android {
    namespace = "com.opencritic.about"
}