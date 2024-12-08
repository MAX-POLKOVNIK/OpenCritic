plugins {
    alias(libs.plugins.feature)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(projects.feature.gameListApi)

            //implementation(projects.feature.auth)
            implementation(projects.feature.games)
            implementation(projects.feature.gameDetailsApi)
            implementation(projects.feature.authApi)
            implementation(projects.feature.aboutApi)
        }
    }
}

android {
    namespace = "com.opencritic.game.your"
}