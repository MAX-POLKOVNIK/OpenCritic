plugins {
    alias(libs.plugins.feature)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(projects.feature.gameBrowserApi)

            implementation(projects.feature.games)
            implementation(projects.feature.gameDetails)
            implementation(projects.feature.gameDetailsApi)
            implementation(projects.feature.calendarApi)
        }
    }
}

android {
    namespace = "com.opencritic.game.browser"
}