plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroid)
    id(Plugins.kotlinAndroidExtensions)
    id(Plugins.kapt)
}
dependencies {
    implementation(CoreLibraries.kotlin)
    api(project(Modules.model))
    implementation(ThirdPartyLibraries.retrofit)
    implementation(CoreLibraries.koin)
    kapt(ThirdPartyLibraries.moshiapt)
    implementation(ThirdPartyLibraries.moshi)
    implementation(ThirdPartyLibraries.moshiConverter)
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

