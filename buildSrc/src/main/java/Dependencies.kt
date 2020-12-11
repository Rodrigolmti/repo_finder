object ApplicationId {
    const val id = "br.com.rodrigolmti.repo_finder"
}

object Versions {
    const val compileSdk = 30
    const val targetSdk = 30
    const val minSdk = 21
}

object GradlePlugin {
    object Versions {
        const val kotlin = "1.4.20"
        const val gradle = "4.1.1"
        const val safeArgs = "2.3.1"
    }

    const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val buildTools = "com.android.tools.build:gradle:${Versions.gradle}"
    const val navigationSafeArgs =
        "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.safeArgs}"
}

object Releases {
    const val versionCode = 1
    const val versionName = "1.0.0"
}
