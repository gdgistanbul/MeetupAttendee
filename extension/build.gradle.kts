plugins {
    kotlin(Plugins.jvm)
}

dependencies {
    implementation(CoreLibraries.kotlin)
    implementation(CoreLibraries.coroutines)
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