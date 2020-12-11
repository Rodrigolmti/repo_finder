import org.gradle.api.Plugin
import org.gradle.api.Project

class DependenciesPlugin : Plugin<Project> {
    override fun apply(target: Project) = Unit
}

object ImageLoad {
    object Versions {
        const val coil = "1.1.0"
    }

    const val coil = "io.coil-kt:coil:${Versions.coil}"
}

object Kotlin {
    object Versions {
        const val kotlin = "1.4.10"
    }

    const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"
    const val junit = "org.jetbrains.kotlin:kotlin-test-junit:${Versions.kotlin}"
}

object JUnit {
    object Versions {
        const val junit = "4.12"
    }

    const val core = "junit:junit:${Versions.junit}"
}

object MockK {
    object Versions {
        const val mockk = "1.9.2"
    }

    const val core = "io.mockk:mockk:${Versions.mockk}"
    const val android = "io.mockk:mockk-android:${Versions.mockk}"
}

object Lifecycle {
    object Versions {
        const val lifecycle = "2.2.0"
        const val lifecycleTesting = "2.1.0"
    }

    const val commonJava8 = "androidx.lifecycle:lifecycle-common-java8:${Versions.lifecycle}"
    const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    const val liveData = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
    const val test = "androidx.arch.core:core-testing:${Versions.lifecycleTesting}"
}

object Room {
    object Versions {
        const val room = "2.2.5"
    }

    const val runtime = "androidx.room:room-runtime:${Versions.room}"
    const val compiler = "androidx.room:room-compiler:${Versions.room}"
    const val ktx = "androidx.room:room-ktx:${Versions.room}"
}

object Moshi {
    object Versions {
        const val moshi = "1.10.0"
    }

    const val coreKotlin = "com.squareup.moshi:moshi-kotlin:${Versions.moshi}"
    const val codeGen = "com.squareup.moshi:moshi-kotlin-codegen:${Versions.moshi}"
}

object Dagger {
    object Versions {
        const val dagger = "2.28.3"
    }

    const val core = "com.google.dagger:dagger:${Versions.dagger}"
    const val android = "com.google.dagger:dagger-android-support:${Versions.dagger}"
    const val compiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"
    const val androidProcessor = "com.google.dagger:dagger-android-processor:${Versions.dagger}"
}

object Coroutines {
    object Versions {
        const val coroutines = "1.3.9"
    }

    const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"
}

object Retrofit {
    object Versions {
        const val retrofit = "2.9.0"
        const val interceptor = "4.8.0"
    }

    const val core = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val moshiConverter = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
    const val interceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.interceptor}"
}

object AndroidX {
    object Versions {
        const val ktx = "1.3.1"
        const val appcompat = "1.2.0"
        const val design = "1.1.0"
        const val recyclerView = "1.1.0"
        const val fragment = "1.2.5"
        const val navVersion = "2.3.1"
    }

    const val ktx = "androidx.core:core-ktx:${Versions.ktx}"
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val design = "com.google.android.material:material:${Versions.design}"
    const val recyclerview = "androidx.recyclerview:recyclerview:${Versions.recyclerView}"
    const val fragment = "androidx.fragment:fragment-ktx:${Versions.fragment}"
    const val navigationFragment =
        "androidx.navigation:navigation-fragment-ktx:${Versions.navVersion}"
    const val navigationUi = "androidx.navigation:navigation-ui-ktx:${Versions.navVersion}"
}

object Layout {
    object Versions {
        const val constraintLayout = "1.1.3"
    }

    const val constraint = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
}



