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
    testImplementation(TestLibraries.mockk)
    testImplementation(TestLibraries.jUnit)
    testImplementation(TestLibraries.archCoreTesting)
    testImplementation(TestLibraries.coroutinesTesting)
}

android {
    compileSdkVersion(Versions.compileSdkVersion)
    defaultConfig {
        minSdkVersion(Versions.minSdkVersion)
        targetSdkVersion(Versions.targetSdkVersion)
    }
    testOptions {
        unitTests.isReturnDefaultValues = true
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
