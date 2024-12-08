plugins {
    alias(libs.plugins.feature)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(projects.feature.newsApi)

            implementation(projects.feature.games)
            implementation(projects.feature.gameListApi)
            implementation(projects.feature.gameDetails)
            implementation(projects.feature.gameDetailsApi)

            implementation(libs.androidx.paging.common)
        }
    }
}

android {
    namespace = "com.opencritic.news"
}