plugins {
    id(Plugins.androidApplication)
    kotlin(Plugins.kotlinAndroid)
    kotlin(Plugins.kotlinAndroidExtensions)
    id(Plugins.navigationSafeArgs)
    id(Plugins.versionsPlugin) version Versions.versionsPluginVersion
}

android {
    compileSdkVersion(Versions.compileSdkVersion)
    defaultConfig {
        applicationId = Config.applicationId
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
    implementation(AndroidxLibraries.material)
    implementation(project(Modules.meetupViewModel))
    implementation(CoreLibraries.koin)
    implementation(CoreLibraries.koinViewModel)

    // Navigation
    implementation(AndroidxLibraries.navigationUI)
    implementation(AndroidxLibraries.navigationFragment)

    // Testing
    testImplementation(TestLibraries.jUnit)
    androidTestImplementation(TestLibraries.runnner)
    androidTestImplementation(TestLibraries.espressoCore)
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = JavaVersion.VERSION_1_8.toString()
}

//apply(mapOf("plugin" to "com.google.gms.google-services"))

