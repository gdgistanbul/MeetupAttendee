plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroid)
    id(Plugins.kotlinAndroidExtensions)
}
dependencies {
    implementation(CoreLibraries.kotlin)
    api(project(Modules.meetupRepo))
    implementation(AndroidxLibraries.lifecycleExtensions)
    implementation(AndroidxLibraries.viewModel)
    implementation(CoreLibraries.koin)
    implementation(CoreLibraries.koinViewModel)
}

android {
    compileSdkVersion(Versions.compileSdkVersion)
    defaultConfig {
        minSdkVersion(Versions.minSdkVersion)
        targetSdkVersion(Versions.targetSdkVersion)
    }
}
buildscript {

    repositories {
        mavenCentral()
    }
    dependencies {
        classpath(ClassPaths.kotlinGradlePluginClasspath)
    }
}
repositories {
    mavenCentral()
}
