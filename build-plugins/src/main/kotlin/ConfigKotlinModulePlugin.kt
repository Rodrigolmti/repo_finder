import org.gradle.api.Plugin
import org.gradle.api.Project

class ConfigKotlinModulePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.plugins.apply("kotlin")
        target.configKotlinModuleRepositories()
    }
}

fun Project.configKotlinModuleRepositories(): Unit = with(dependencies) {
    addCoroutinesCore()
    addKotlin()
    addJUnit()
}
