object ClassPaths {
    val gradleClasspath = "com.android.tools.build:gradle:${Versions.gradleVersion}"
    val kotlinGradlePluginClasspath =
        "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlinVersion}"
    val kotlinSerializationClasspath =
        "org.jetbrains.kotlin:kotlin-serialization:${Versions.kotlinVersion}"
    val navigationSafeArgsClasspath =
        "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigationVersion}"
}