plugins {
    alias(libs.plugins.feature)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(projects.feature.gameDetailsApi)

            implementation(projects.feature.games)
            implementation(projects.feature.gameListApi)
            implementation(projects.feature.gameListImpl)
            implementation(projects.feature.authApi)
        }
    }
}

android {
    namespace = "com.opencritic.games.details"
}