plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroid)
    id(Plugins.kotlinAndroidExtensions)
}

android {
    compileSdkVersion(Versions.compileSdkVersion)
    defaultConfig {
        minSdkVersion(Versions.minSdkVersion)
        targetSdkVersion(Versions.targetSdkVersion)
    }
}
dependencies {
    implementation(CoreLibraries.kotlin)
    api(CoreLibraries.coroutines)
    api(project(Modules.meetupApi))
    implementation(FirebaseLibraries.core)
    implementation(FirebaseLibraries.database)
    implementation(CoreLibraries.koin)
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
