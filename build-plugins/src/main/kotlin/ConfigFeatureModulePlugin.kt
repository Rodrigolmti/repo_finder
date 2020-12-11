import org.gradle.api.Plugin
import org.gradle.api.Project

class ConfigFeatureModulePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.plugins.apply("com.android.library")
        target.plugins.apply("kotlin-android")
        target.plugins.apply("kotlin-kapt")
        target.plugins.apply("androidx.navigation.safeargs.kotlin")
        target.configFeatureModuleRepositories()
    }
}

fun Project.configFeatureModuleRepositories(): Unit = with(dependencies) {
    addKtx()
    addAppcompat()
    addConstraintLayout()

    addNavigationFragment()
    addNavigationUi()

    addKotlin()
    addCoroutines()
    addJUnit()
    addMocks()
    addLifecycle()
    addFragment()

    addCoreAndroid()
}
