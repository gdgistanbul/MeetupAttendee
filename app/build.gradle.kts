plugins {
    id(Plugins.androidApplication)
    id(Plugins.kotlinAndroid)
    id(Plugins.kotlinAndroidExtensions)
}

android {
    compileSdkVersion(Versions.compileSdkVersion)
    defaultConfig {
        applicationId = Config.applicatiınId
        minSdkVersion(Versions.minSdkVersion)
        targetSdkVersion(Versions.targetSdkVersion)
        versionCode = Release.versionCode
        versionName = Release.versionName
        testInstrumentationRunner = Config.testInstrumentationRunner
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }
}

dependencies {
    // Core Libraries
    implementation(CoreLibraries.kotlin)

    // Support Libraries
    implementation(AndroidxLibraries.appCompat)
    implementation(AndroidxLibraries.core)
    implementation(AndroidxLibraries.constraintLayout)
    implementation(AndroidxLibraries.lifecycleExtensions)
    implementation(project(Modules.meetupViewModel))
    implementation(project(Modules.meetupRepo))
    implementation(project(Modules.meetupApi))
    implementation(CoreLibraries.koin)
    implementation(CoreLibraries.koinViewModel)

    // Testing
    testImplementation(TestLibraries.jUnit)
    androidTestImplementation(TestLibraries.runnner)
    androidTestImplementation(TestLibraries.espressoCore)
}

apply(mapOf("plugin" to "com.google.gms.google-services"))