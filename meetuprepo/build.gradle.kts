plugins {
    id(Plugins.androidLibrary)
    kotlin(Plugins.kotlinAndroid)
    kotlin(Plugins.kotlinAndroidExtensions)
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
    implementation(ThirdPartyLibraries.moshi)
    implementation(FirebaseLibraries.database)
    implementation(CoreLibraries.koin)
    implementation(AndroidxLibraries.security)
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
