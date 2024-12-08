import com.android.build.api.dsl.LibraryExtension
import com.opencritic.build_logic.convention.configureKotlinAndroid
import com.opencritic.build_logic.convention.configureKotlinMultiplatformFeature
import com.opencritic.build_logic.convention.debugImplementation
import com.opencritic.build_logic.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class KotlinMultiplatformFeaturePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply(libs.findPlugin("androidLibrary").get().get().pluginId)
                apply(libs.findPlugin("kotlinMultiplatform").get().get().pluginId)
                apply(libs.findPlugin("compose-compiler").get().get().pluginId)
                apply(libs.findPlugin("kotlin-serialization").get().get().pluginId)
            }
            extensions.configure<KotlinMultiplatformExtension>(::configureKotlinMultiplatformFeature)
            extensions.configure<LibraryExtension>(::configureKotlinAndroid)

            dependencies {
                debugImplementation(libs.findLibrary("compose-ui-tooling").get())
            }

            task("testClasses")
        }
    }
}