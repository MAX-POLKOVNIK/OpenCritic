enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "OpenCritic"
include(":androidApp")
include(":shared")
include(":api")
include(":logs")
include(":mvvm")
include(":resources")
include(":games")
include(":dashboard")
include(":main")
include(":navigation")
include(":game-details")
include(":search")
