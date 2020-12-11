import org.gradle.api.Project
import org.gradle.kotlin.dsl.closureOf

object AndroidXLibs {
    val navigationFragment = closureOf<Project> { addNavigationFragment() }
    val constraintLayout = closureOf<Project> { addConstraintLayout() }
    val navigationUi = closureOf<Project> { addNavigationUi() }
    val recyclerview = closureOf<Project> { addRecyclerview() }
    val appcompat = closureOf<Project> { addAppcompat() }
    val fragment = closureOf<Project> { addFragment() }
    val design = closureOf<Project> { addDesign() }
    val ktx = closureOf<Project> { addKtx() }
}

object Libs {
    val coroutinesCore = closureOf<Project> { addCoroutinesCore() }
    val daggerAndroid = closureOf<Project> { addDaggerAndroid() }
    val coroutines = closureOf<Project> { addCoroutines() }
    val daggerCore = closureOf<Project> { addDaggerCore() }
    val lifecycle = closureOf<Project> { addLifecycle() }
    val retrofit = closureOf<Project> { addRetrofit() }
    val kotlin = closureOf<Project> { addKotlin() }
    val jUnit = closureOf<Project> { addJUnit() }
    val mocks = closureOf<Project> { addMocks() }
}

fun Project.addKtx() = with(dependencies) { add("implementation", AndroidX.ktx) }
fun Project.addFragment() = with(dependencies) { add("implementation", AndroidX.fragment) }
fun Project.addAppcompat() = with(dependencies) { add("implementation", AndroidX.appcompat) }
fun Project.addDesign() = with(dependencies) { add("implementation", AndroidX.design) }
fun Project.addRecyclerview() = with(dependencies) { add("implementation", AndroidX.recyclerview) }
fun Project.addConstraintLayout() = with(dependencies) { add("implementation", Layout.constraint) }

fun Project.addNavigationUi() =
    with(dependencies) { add("implementation", AndroidX.navigationFragment) }

fun Project.addNavigationFragment() =
    with(dependencies) { add("implementation", AndroidX.navigationUi) }

fun Project.addCoroutinesCore() = with(dependencies) {
    add("implementation", Coroutines.core)
    add("testImplementation", Coroutines.core)
}

fun Project.addCoroutines() = with(dependencies) {
    add("implementation", Coroutines.core)
    add("implementation", Coroutines.android)
    add("testImplementation", Coroutines.test)
    add("testImplementation", Coroutines.core)
}

fun Project.addKotlin() = with(dependencies) {
    add("implementation", Kotlin.stdlib)
}

fun Project.addDaggerCore() = with(dependencies) {
    add("implementation", Dagger.core)
}

fun Project.addDaggerAndroid() = with(dependencies) {
    add("implementation", Dagger.android)
}

fun Project.addJUnit() = with(dependencies) {
    add("testImplementation", JUnit.core)
    add("testImplementation", Kotlin.junit)
}

fun Project.addMocks() = with(dependencies) {
    add("testImplementation", MockK.core)
}

fun Project.addLifecycle() = with(dependencies) {
    add("implementation", Lifecycle.viewModel)
    add("implementation", Lifecycle.liveData)
    add("testImplementation", Lifecycle.test)
}

fun Project.addRetrofit() = with(dependencies) {
    add("implementation", Retrofit.core)
}

fun Project.addCoreAndroid() = with(dependencies) {
    add("implementation", project(Modules.coreAndroid))
}