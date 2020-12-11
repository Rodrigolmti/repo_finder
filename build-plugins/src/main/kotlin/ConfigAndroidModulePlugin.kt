import org.gradle.api.Plugin
import org.gradle.api.Project

class ConfigAndroidModulePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.plugins.apply("com.android.library")
        target.plugins.apply("kotlin-android")
        target.plugins.apply("kotlin-android-extensions")
        target.configAndroidModuleRepositories()
    }
}

fun Project.configAndroidModuleRepositories(): Unit = with(dependencies) {
    addKotlin()
}
