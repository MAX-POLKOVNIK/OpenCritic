plugins {
    alias(libs.plugins.feature)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.feature.gameDetails)
            implementation(projects.feature.search)
            implementation(projects.feature.gameBrowser)
            implementation(projects.feature.gameListApi)
            implementation(projects.feature.news)
            implementation(projects.feature.dashboard)
            implementation(projects.feature.gameListImpl)
        }
    }
}

android {
    namespace = "com.opencritic.main"
}
