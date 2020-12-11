plugins {
    `kotlin-dsl`
    `java-gradle-plugin`
}

repositories {
    jcenter()
}

gradlePlugin {
    plugins.create("dependencies") {
        id = "dep"
        implementationClass = "DependenciesPlugin"
    }
    plugins.create("config-feature-module") {
        id = "config-feature-module"
        implementationClass = "ConfigFeatureModulePlugin"
    }
    plugins.create("config-android-module") {
        id = "config-android-module"
        implementationClass = "ConfigAndroidModulePlugin"
    }
    plugins.create("config-kotlin-module") {
        id = "config-kotlin-module"
        implementationClass = "ConfigKotlinModulePlugin"
    }
}
