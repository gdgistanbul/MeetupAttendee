plugins {
    kotlin(Plugins.jvm)
    kotlin(Plugins.kapt)
}
dependencies {
    implementation(CoreLibraries.kotlin)
    implementation(ThirdPartyLibraries.moshi)
    "kapt"(ThirdPartyLibraries.moshiapt)
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
