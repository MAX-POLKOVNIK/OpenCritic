plugins {
    alias(libs.plugins.feature)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(projects.feature.hallOfFameApi)

            implementation(projects.feature.games)
            implementation(projects.feature.gameDetails)
            implementation(projects.feature.gameDetailsApi)
        }
    }
}

android {
    namespace = "com.opencritic.halloffame"
}