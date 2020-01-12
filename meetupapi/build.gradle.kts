plugins {
    id(Plugins.androidLibrary)
    kotlin(Plugins.kotlinAndroid)
    kotlin(Plugins.kotlinAndroidExtensions)
    kotlin(Plugins.kapt)
}
dependencies {
    implementation(CoreLibraries.kotlin)
    api(project(Modules.model))
    implementation(ThirdPartyLibraries.retrofit)
    implementation(CoreLibraries.koin)
    "kapt"(ThirdPartyLibraries.moshiapt)
    implementation(ThirdPartyLibraries.moshi)
    implementation(ThirdPartyLibraries.moshiConverter)
    implementation(AndroidxLibraries.security)
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
        classpath(ClassPaths.kotlinSerializationClasspath)
    }
}



repositories {
    mavenCentral()
}

