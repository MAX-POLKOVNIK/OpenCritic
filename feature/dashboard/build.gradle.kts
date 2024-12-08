plugins {
    alias(libs.plugins.feature)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(projects.feature.dashboardApi)

            implementation(projects.feature.games)
            implementation(projects.feature.gameBrowserApi)
            implementation(projects.feature.gameDetailsApi)
            implementation(projects.feature.hallOfFameApi)
        }
    }
}

android {
    namespace = "com.opencritic.dashboard"
}