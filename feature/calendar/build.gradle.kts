plugins {
    alias(libs.plugins.feature)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(projects.feature.calendarApi)
            implementation(projects.feature.games)
            implementation(projects.feature.gameDetailsApi)
        }
    }
}

android {
    namespace = "com.opencritic.calendar"
}
