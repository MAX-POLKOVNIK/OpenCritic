enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    @Suppress("UnstableApiUsage")
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "OpenCritic"
include(":androidApp")
include(":shared")
include(":core:api")
include(":core:logs")
include(":core:mvvm")
include(":core:resources")
include(":feature:games")
include(":feature:dashboard")
include(":feature:main")
include(":core:navigation")
include(":feature:game-details")
include(":feature:search")
include(":feature:game-browser")
include(":core:database")
include(":feature:game-list-impl")
include(":feature:auth-impl")
include(":feature:news")
include(":feature:calendar")
include(":feature:about")
include(":feature:hall-of-fame")
include(":feature:game-details-api")
include(":feature:auth-api")
include(":feature:about-api")
include(":feature:game-list-api")
include(":feature:news-api")
include(":feature:game-browser-api")
include(":feature:calendar-api")
include(":feature:hall-of-fame-api")
include(":feature:dashboard-api")
include(":feature:search-api")
