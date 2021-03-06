plugins {
    kotlin(Plugins.jvm)
    kotlin(Plugins.kapt)
}
dependencies {
    api(project(Modules.extension))
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
